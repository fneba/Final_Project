package com.company.gamestore.controllers;

import com.company.gamestore.models.Invoice;
import com.company.gamestore.models.Tshirt;
import com.company.gamestore.repositories.InvoiceRepository;
import com.company.gamestore.repositories.ProcessingFeeRepository;
import com.company.gamestore.repositories.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class InvoiceController {

    @Autowired
    InvoiceRepository invoiceRepository;

//    @Autowired
//    TaxRepository taxRepo;
//
//    @Autowired
//    ProcessingFeeRepository proFeeRepo;

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
