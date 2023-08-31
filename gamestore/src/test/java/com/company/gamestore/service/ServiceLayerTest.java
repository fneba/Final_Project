package com.company.gamestore.service;



import com.company.gamestore.models.*;
import com.company.gamestore.repositories.*;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;


class ServiceLayerTest {

    ServiceLayer service;

    TaxRepository taxRepo;

    ProcessingFeeRepository proFeeRepo;

    ConsoleRepository consoleRepo;

    TshirtRepository tshirtRepo;

    GameRepository gameRepo;

    InvoiceRepository invoiceRepo;

    @BeforeEach
    public void setUp() throws Exception {
        setUpTaxRepositoryMock();
        setUpProcessingFeeRepositoryMock();
        setUpConsoleRepositoryMock();
        setUpTshirtRepositoryMock();
        setUpGameRepositoryMock();
        setUpInvoiceRepositoryMock();

        service = new ServiceLayer(tshirtRepo, consoleRepo, gameRepo, invoiceRepo, taxRepo, proFeeRepo);
    }

    @Test
    public void  shouldCreateAndFindInvoice() {
        InvoiceViewModel toSave = new InvoiceViewModel();
        toSave.setId(1);
        toSave.setCity("New York");
        toSave.setQuantity(5);
        toSave.setName("Josh");
        toSave.setItemId(1);
        toSave.setItemType("type");
        toSave.setState("NY");
        toSave.setProcessingFee(1.99);
        toSave.setTax(12.99);
        toSave.setStreet("fake st");
        toSave.setSubtotal(100.45);
        toSave.setTotal(113.15);
        toSave.setZipCode("11111");
        toSave.setUnitPrice(12.99);

        InvoiceViewModel invoiceViewModel = service.findInvoiceById(1);
        assertEquals(invoiceViewModel.getId(), toSave.getId());
    }

    // Below are tests that haven't been completed yet but were commented out so groupmates can run completed
    // test

    @Test
    public void shouldFindInvoicesByName() {
        InvoiceViewModel toFind = new InvoiceViewModel();
        toFind.setId(1);
        toFind.setCity("New York");
        toFind.setQuantity(5);
        toFind.setName("Josh");
        toFind.setItemId(1);
        toFind.setItemType("type");
        toFind.setState("NY");
        toFind.setProcessingFee(1.99);
        toFind.setTax(12.99);
        toFind.setStreet("fake st");
        toFind.setSubtotal(100.45);
        toFind.setTotal(113.15);
        toFind.setZipCode("11111");
        toFind.setUnitPrice(12.99);

        List<InvoiceViewModel> invoiceViewModel = service.findInvoicesByName("Josh");
        assertEquals(invoiceViewModel.get(0).getName(), toFind.getName());

    }

//    @Test
//    public void shouldFindInvoiceById() {
//        InvoiceViewModel toFind = new InvoiceViewModel();
//        toFind.setId(1);
//        toFind.setCity("New York");
//        toFind.setQuantity(5);
//        toFind.setName("Josh");
//        toFind.setItemId(1);
//        toFind.setItemType("type");
//        toFind.setState("NY");
//        toFind.setProcessingFee(new BigDecimal("1.99"));
//        toFind.setTax(new BigDecimal("12.99"));
//        toFind.setStreet("fake st");
//        toFind.setSubtotal(new BigDecimal("100.45"));
//        toFind.setTotal(new BigDecimal("113.15"));
//        toFind.setZipCode("11111");
//        toFind.setUnitPrice(new BigDecimal("12.99"));
//
//        InvoiceViewModel invoiceViewModel = service.findInvoiceById(1);
//        assertEquals(invoiceViewModel, toFind);
//
//    }

    @Test
    public void shouldFindInvoices() {
        InvoiceViewModel invoice = new InvoiceViewModel();
        invoice.setId(1);
        invoice.setCity("New York");
        invoice.setQuantity(5);
        invoice.setName("Josh");
        invoice.setItemId(1);
        invoice.setItemType("type");
        invoice.setState("NY");
        invoice.setProcessingFee(1.99);
        invoice.setTax(12.99);
        invoice.setStreet("fake st");
        invoice.setSubtotal(100.45);
        invoice.setTotal(113.15);
        invoice.setZipCode("11111");
        invoice.setUnitPrice(12.99);

        InvoiceViewModel invoice2 = new InvoiceViewModel();
        invoice2.setId(1);
        invoice2.setCity("New York");
        invoice2.setQuantity(5);
        invoice2.setName("Josh");
        invoice2.setItemId(1);
        invoice2.setItemType("type");
        invoice2.setState("NY");
        invoice2.setProcessingFee(1.99);
        invoice2.setTax(12.99);
        invoice2.setStreet("fake st");
        invoice2.setSubtotal(100.45);
        invoice2.setTotal(113.15);
        invoice2.setZipCode("11111");
        invoice2.setUnitPrice(12.99);

        List<InvoiceViewModel> invoiceViewModels = service.findInvoices();
        assertEquals(2, invoiceViewModels.size());

    }








    // Helper methods

    private void setUpTaxRepositoryMock() {
        taxRepo = mock(TaxRepository.class);
        Tax tax = new Tax();
        tax.setState("MD");
        tax.setRate(0.06);

        Tax tax2 = new Tax();
        tax2.setState("MD");
        tax2.setRate(0.06);

        doReturn(tax.getRate()).when(taxRepo).findRateByState("MD");
    }

    private void setUpProcessingFeeRepositoryMock() {
        proFeeRepo = mock(ProcessingFeeRepository.class);
        Fee fee = new Fee();
        fee.setProductType("game");
        fee.setFee(5.99);

        Fee fee2 = new Fee();
        fee2.setProductType("game");
        fee2.setFee(5.99);

        doReturn(fee.getFee()).when(proFeeRepo).findFeeByProductType("game");
    }

    private void setUpConsoleRepositoryMock() {
        consoleRepo = mock(ConsoleRepository.class);
        Console console = new Console();
        console.setId(1);
        console.setQuantity(5);
        console.setManufacturer("Sony");
        console.setModel("PS5");
        console.setPrice(499.99);
        console.setMemoryAmount("1000000");
        console.setProcessor("Intel I9");

        Console console2 = new Console();
        console2.setId(1);
        console2.setQuantity(5);
        console2.setManufacturer("Sony");
        console2.setModel("PS5");
        console2.setPrice(499.99);
        console2.setMemoryAmount("1000000");
        console2.setProcessor("Intel I9");

        List<Console> consoleList = new ArrayList<>();
        consoleList.add(console);

        doReturn(Optional.of(console)).when(consoleRepo).findById(1);
    }

    private void setUpTshirtRepositoryMock() {
        tshirtRepo = mock(TshirtRepository.class);
        Tshirt tshirt = new Tshirt();
        tshirt.setId(1);
        tshirt.setColor("Red");
        tshirt.setDescription("Big red tshirt.");
        tshirt.setSize("Large");
        tshirt.setQuantity(5);
        tshirt.setPrice(5.99);

        Tshirt tshirt2 = new Tshirt();
        tshirt2.setId(1);
        tshirt2.setColor("Red");
        tshirt2.setDescription("Big red tshirt.");
        tshirt2.setSize("Large");
        tshirt2.setQuantity(5);
        tshirt2.setPrice(5.99);

        List<Tshirt> tshirtList = new ArrayList<>();
        tshirtList.add(tshirt);

        doReturn(Optional.of(tshirt)).when(tshirtRepo).findById(1);
    }

    private void setUpGameRepositoryMock() {
        gameRepo = mock(GameRepository.class);
        Game game = new Game();
        game.setGameId(1);
        game.setTitle("Horizom");
        game.setDescription("Fun");
        game.setEsrbRating("R");
        game.setQuantity(5);
        game.setStudio("Insomnia");
        game.setPrice(59.99);

        Game game2 = new Game();
        game2.setGameId(1);
        game2.setTitle("Horizom");
        game2.setDescription("Fun");
        game2.setEsrbRating("R");
        game2.setQuantity(5);
        game2.setStudio("Insomnia");
        game2.setPrice(59.99);

        doReturn(Optional.of(game)).when(gameRepo).findById(1);
    }

    private void setUpInvoiceRepositoryMock() {
        invoiceRepo = mock(InvoiceRepository.class);
        Invoice invoice = new Invoice();
        invoice.setId(1);
        invoice.setCity("New York");
        invoice.setQuantity(5);
        invoice.setName("Josh");
        invoice.setItemId(1);
        invoice.setItemType("type");
        invoice.setState("NY");
        invoice.setProcessingFee(1.99);
        invoice.setTax(12.99);
        invoice.setStreet("fake st");
        invoice.setSubtotal(100.45);
        invoice.setTotal(113.15);
        invoice.setZipCode("11111");
        invoice.setUnitPrice(12.99);

        Invoice invoice2 = new Invoice();
        invoice2.setId(1);
        invoice2.setCity("New York");
        invoice2.setQuantity(5);
        invoice2.setName("Josh");
        invoice2.setItemId(1);
        invoice2.setItemType("type");
        invoice2.setState("NY");
        invoice2.setProcessingFee(1.99);
        invoice2.setTax(12.99);
        invoice2.setStreet("fake st");
        invoice2.setSubtotal(100.45);
        invoice2.setTotal(113.15);
        invoice2.setZipCode("11111");
        invoice2.setUnitPrice(12.99);

        List<Invoice> invList = new ArrayList<>();
        invList.add(invoice);
        invList.add(invoice2);

        doReturn(invoice).when(invoiceRepo).save(invoice2);
        doReturn(Optional.of(invoice)).when(invoiceRepo).findById(1);
        doReturn(invList).when(invoiceRepo).findAll();
        doReturn(invList).when(invoiceRepo).findByName("Josh");
    }

}