package com.company.gamestore.service;


import com.company.gamestore.models.Invoice;
import com.company.gamestore.repositories.ConsoleRepository;
import com.company.gamestore.repositories.GameRepository;
import com.company.gamestore.repositories.InvoiceRepository;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import com.company.gamestore.repositories.TshirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceLayer {

    private TshirtRepository tshirtRepository;

    private ConsoleRepository consoleRepository;

    private GameRepository gameRepository;

    private InvoiceRepository invoiceRepository;

    // hardwire repos (we'll need all of them)
    @Autowired
    public ServiceLayer (TshirtRepository tshirtRepository, ConsoleRepository consoleRepository,
                         GameRepository gameRepository, InvoiceRepository invoiceRepository){
        this.tshirtRepository = tshirtRepository;
        this.consoleRepository = consoleRepository;
        this.gameRepository = gameRepository;
        this.invoiceRepository = invoiceRepository;
    }


}
