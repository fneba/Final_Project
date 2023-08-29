package com.company.gamestore.controllers;

import com.company.gamestore.models.Invoice;
import com.company.gamestore.repositories.InvoiceRepository;
import com.company.gamestore.repositories.ProcessingFeeRepository;
import com.company.gamestore.repositories.TaxRepository;
import com.company.gamestore.service.ServiceLayer;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class InvoiceController {

    // The commented out paths are the invoice controller paths using service layer
    // left commented until discussion with group

//    @Autowired
//    ServiceLayer serviceLayer;

//    // Create
//    @PostMapping("/invoices")
//    @ResponseStatus(HttpStatus.CREATED)
//    public InvoiceViewModel createInvoice (@RequestBody InvoiceViewModel invoiceViewModel) {
//
//        return serviceLayer.createInvoice(invoiceViewModel);
//    }

//    // Read
//    @GetMapping("/invoices/{id}")
//    public InvoiceViewModel getInvoiceById(@PathVariable int id){
//        return serviceLayer.findInvoiceById(id);
//    }

//    // Read All
//    @GetMapping("/invoices")
//    public List<InvoiceViewModel> getInvoices(){
//        return serviceLayer.findInvoices();
//    }

//    // Custom Method
//    @GetMapping("/invoices/invoice/{name}")
//    public List<InvoiceViewModel> getInvoiceByCustomerName(@PathVariable String name) {
//        return serviceLayer.findInvoicesByName(name);
//    }


    @Autowired
    InvoiceRepository invoiceRepository;


    // Create
    @PostMapping("/invoices")
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createInvoice (@RequestBody Invoice invoice) {

        return invoiceRepository.save(invoice);
    }

    // Read
    @GetMapping("/invoices/{id}")
    public Invoice getInvoiceById(@PathVariable int id){
        Optional<Invoice> returnVal = invoiceRepository.findById(id);
        if (returnVal.isPresent()){
            return returnVal.get();
        } else {
            return null;
        }
    }

    // Read All
    @GetMapping("/invoices")
    public List<Invoice> getInvoices(){
        return invoiceRepository.findAll();
    }

    // Custom Method
    @GetMapping("/invoices/invoice/{name}")
    public List<Invoice> getInvoiceByCustomerName(@PathVariable String name) {
        return invoiceRepository.findByName(name);
    }
}
