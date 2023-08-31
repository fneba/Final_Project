package com.company.gamestore.repositories;


import com.company.gamestore.models.Invoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InvoiceRepositoryTest {

    @Autowired
    InvoiceRepository invoiceRepo;

    @MockBean
    TaxRepository taxRepo;

    @MockBean
    ProcessingFeeRepository proFeeRepo;


    @BeforeEach
    public void setUp() throws Exception {
        invoiceRepo.deleteAll();


    }

    @Test
    public void shouldGetAllInvoices(){

        // ARRANGE
        Invoice invoice = new Invoice();
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

        // ARRANGE
        Invoice invoice2 = new Invoice();
        invoice2.setCity("Hyattsville");
        invoice2.setQuantity(10);
        invoice2.setName("Kevin");
        invoice2.setItemId(2);
        invoice2.setItemType("types");
        invoice2.setState("MD");
        invoice2.setProcessingFee(2.99);
        invoice2.setTax(10.99);
        invoice2.setStreet("faker st");
        invoice2.setSubtotal(200.25);
        invoice2.setTotal(210.24);
        invoice2.setZipCode("22222");
        invoice2.setUnitPrice(11.99);


        invoice = invoiceRepo.save(invoice);
        invoice2 = invoiceRepo.save(invoice2);

        // ASSERT
        List<Invoice> invoiceList = invoiceRepo.findAll();

        assertEquals(2, invoiceList.size());
    }

    @Test
    public void shouldGetInvoiceById(){
        // ARRANGE
        Invoice invoice = new Invoice();
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

        // ARRANGE
        Invoice invoice2 = new Invoice();
        invoice2.setCity("Hyattsville");
        invoice2.setQuantity(10);
        invoice2.setName("Kevin");
        invoice2.setItemId(2);
        invoice2.setItemType("types");
        invoice2.setState("MD");
        invoice2.setProcessingFee(2.99);
        invoice2.setTax(10.99);
        invoice2.setStreet("faker st");
        invoice2.setSubtotal(200.25);
        invoice2.setTotal(210.24);
        invoice2.setZipCode("22222");
        invoice2.setUnitPrice(11.99);

        invoice = invoiceRepo.save(invoice);
        invoice2 = invoiceRepo.save(invoice2);

        // ASSERT
        Invoice invoiceGot = invoiceRepo.findById(invoice.getId()).orElse(null);

        assertNotNull(invoiceGot);
        assertEquals("New York", invoiceGot.getCity());
        assertEquals("type", invoiceGot.getItemType());
        assertEquals("NY", invoiceGot.getState());
        assertEquals(5, invoiceGot.getQuantity());

    }

    @Test
    public void shouldGetInvoiceByCustomerName(){

        // ARRANGE
        Invoice invoice = new Invoice();
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

        // ARRANGE
        Invoice invoice2 = new Invoice();
        invoice2.setCity("Hyattsville");
        invoice2.setQuantity(10);
        invoice2.setName("Kevin");
        invoice2.setItemId(2);
        invoice2.setItemType("types");
        invoice2.setState("MD");
        invoice2.setProcessingFee(2.99);
        invoice2.setTax(10.99);
        invoice2.setStreet("faker st");
        invoice2.setSubtotal(200.25);
        invoice2.setTotal(210.24);
        invoice2.setZipCode("22222");
        invoice2.setUnitPrice(11.99);


        invoice = invoiceRepo.save(invoice);
        invoice2 = invoiceRepo.save(invoice2);

        // ASSERT
        List<Invoice> invoiceList = invoiceRepo.findByName("Josh");

        assertEquals(1, invoiceList.size());
    }



    @Test
    public void shouldCreateInvoice(){

        // ARRANGE
        Invoice invoice = new Invoice();
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


        Invoice invoice1 = invoiceRepo.save(invoice);
        assertEquals(invoice.getCity(), invoice1.getCity());

    }

}