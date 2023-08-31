package com.company.gamestore.service;


import com.company.gamestore.models.Invoice;
import com.company.gamestore.repositories.*;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceLayer {

    private TshirtRepository tshirtRepository;

    private ConsoleRepository consoleRepository;

    private GameRepository gameRepository;

    private InvoiceRepository invoiceRepository;

    private TaxRepository taxRepository;

    private ProcessingFeeRepository procFeeRepository;

    // hardwire repos (we'll need all of them)
    @Autowired
    public ServiceLayer (TshirtRepository tshirtRepository, ConsoleRepository consoleRepository,
                         GameRepository gameRepository, InvoiceRepository invoiceRepository,
                        TaxRepository taxRepository, ProcessingFeeRepository procFeeRepository){
        this.tshirtRepository = tshirtRepository;
        this.consoleRepository = consoleRepository;
        this.gameRepository = gameRepository;
        this.invoiceRepository = invoiceRepository;
        this.taxRepository = taxRepository;
        this.procFeeRepository = procFeeRepository;
    }

    public InvoiceViewModel findInvoiceById(int id) {

        // Get the album object first
        Optional<Invoice> invoice = invoiceRepository.findById(1);

        return invoice.isPresent() ? buildInvoiceViewModel(invoice.get()) : null;
    }

    // find invoice(s) by name
    public List<InvoiceViewModel> findInvoicesByName(String name) {

        // Get the album object first
        List<Invoice> invoices = invoiceRepository.findByName(name);

        List<InvoiceViewModel> ivmList = new ArrayList<>();

        for (Invoice invoice: invoices){
            // tranform invoice into ivm
            InvoiceViewModel ivm = buildInvoiceViewModel(invoice);
            // add avm to list
            ivmList.add(ivm);
        }
        // return a list of album view models
        return ivmList;
    }

    public List<InvoiceViewModel> findInvoices() {

        // Get the album object first
        List<Invoice> invoices = invoiceRepository.findAll();

        List<InvoiceViewModel> ivmList = new ArrayList<>();

        for (Invoice invoice: invoices){
            // transform invoice into ivm
            InvoiceViewModel ivm = buildInvoiceViewModel(invoice);
            // add avm to list
            ivmList.add(ivm);
        }
        // return a list of album view models
        return ivmList;
    }

    // Create an Invoice
    @Transactional
    public InvoiceViewModel createInvoice(InvoiceViewModel viewModel){

        Invoice inv = new Invoice();
        inv.setName(viewModel.getName());
        inv.setStreet(viewModel.getStreet());
        inv.setCity(viewModel.getCity());
        inv.setState(viewModel.getState());
        inv.setZipCode(viewModel.getZipCode());
        inv.setItemType(viewModel.getItemType());
        inv.setItemId(viewModel.getItemId());
        inv.setQuantity(viewModel.getQuantity());


        if (!"game".equalsIgnoreCase(inv.getItemType()) || !"tshirt".equalsIgnoreCase(inv.getItemType()) || !"console".equalsIgnoreCase(inv.getItemType())) {
            throw new IllegalArgumentException("Please enter game, console or tshirt");
        }
        else {
            if (inv.getQuantity() < 0) {
                throw new IllegalArgumentException("Quantity must be greater than zero");
            }
            if (!taxRepository.findById(inv.getState()).isPresent()){
                throw new IllegalArgumentException("Input two letter State code");
            }
            if ("game".equalsIgnoreCase(inv.getItemType())) {
                if (gameRepository.findById(inv.getItemId()).isPresent()) {
                    if (gameRepository.findById(inv.getItemId()).get().getQuantity() > inv.getQuantity()) {
                        // update repo quantity
                        gameRepository.findById(inv.getItemId()).get().setQuantity(gameRepository.findById(inv.getItemId()).get().getQuantity() - inv.getQuantity());

                        // set unitPrice
                        inv.setUnitPrice(gameRepository.findById(inv.getItemId()).get().getPrice());


                    } else {
                        throw new IllegalArgumentException("Quantity not in stock");
                    }
                } else {
                    throw new IllegalArgumentException("ItemId not found");
                }
            } else if ("tshirt".equalsIgnoreCase(inv.getItemType())) {
                if (tshirtRepository.findById(inv.getItemId()).isPresent()) {
                    if (tshirtRepository.findById(inv.getItemId()).get().getQuantity() > inv.getQuantity()) {
                        // update repo quantity
                        tshirtRepository.findById(inv.getItemId()).get().setQuantity(tshirtRepository.findById(inv.getItemId()).get().getQuantity() - inv.getQuantity());

                        // set unitPrice
                        inv.setUnitPrice(tshirtRepository.findById(inv.getItemId()).get().getPrice());
                    } else {
                        throw new IllegalArgumentException("Quantity not in stock.");
                    }

                } else {
                    throw new IllegalArgumentException("ItemId not found");
                }

            } else if ("console".equalsIgnoreCase(inv.getItemType())) {
                if (consoleRepository.findById(inv.getItemId()).isPresent()) {
                    if (consoleRepository.findById(inv.getItemId()).get().getQuantity() > inv.getQuantity()) {
                        // update repo quantity
                        consoleRepository.findById(inv.getItemId()).get().setQuantity(consoleRepository.findById(inv.getItemId()).get().getQuantity() - inv.getQuantity());

                        // set unitPrice
                        inv.setUnitPrice(consoleRepository.findById(inv.getItemId()).get().getPrice());


                    } else {
                        throw new IllegalArgumentException("Quantity not in stock.");
                    }

                } else {
                    throw new IllegalArgumentException("ItemId not found");
                }

            }
        }

        inv.setSubtotal(inv.getUnitPrice() * inv.getQuantity());

        // Assume state is correct and calculate tax
        inv.setTax(taxRepository.findRateByState(inv.getState()) * (inv.getSubtotal()));

        // get & set processing fee
        inv.setProcessingFee(procFeeRepository.findFeeByProductType(inv.getItemType()));

        if (inv.getQuantity() > 10){
            inv.setProcessingFee(inv.getProcessingFee() + 15.49);
        }

        // calculate and set total
        inv.setTotal(inv.getSubtotal() + (inv.getTax())+(inv.getProcessingFee()));

        // maxtotal error handling

        if (inv.getTotal()> 999.99){
            throw new IllegalArgumentException("Total cannot exceed $999.99, please reduce quantity");
        }

        inv = invoiceRepository.save(inv);

        return buildInvoiceViewModel(inv);


    }

    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice){
        // get invoice
        Optional<Invoice> inv = invoiceRepository.findById(invoice.getId());

        InvoiceViewModel ivm = new InvoiceViewModel();
        ivm.setId(invoice.getId());
        ivm.setName(invoice.getName());
        ivm.setStreet(invoice.getStreet());
        ivm.setCity(invoice.getCity());
        ivm.setState(invoice.getState());
        ivm.setZipCode(invoice.getZipCode());
        ivm.setItemType(invoice.getItemType());
        ivm.setItemId(invoice.getItemId());
        ivm.setQuantity(invoice.getQuantity());
        ivm.setUnitPrice(invoice.getUnitPrice());
        ivm.setSubtotal(invoice.getSubtotal());
        ivm.setTax(invoice.getTax());
        ivm.setProcessingFee(invoice.getProcessingFee());
        ivm.setTotal(invoice.getTotal());

        return ivm;
    }


}
