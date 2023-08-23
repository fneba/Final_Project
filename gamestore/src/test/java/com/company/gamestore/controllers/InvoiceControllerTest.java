package com.company.gamestore.controllers;

import com.company.gamestore.models.Invoice;
import com.company.gamestore.repositories.InvoiceRepository;
import com.company.gamestore.repositories.ProcessingFeeRepository;
import com.company.gamestore.repositories.TaxRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(InvoiceController.class)
class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    InvoiceRepository invoiceRepo;

    @MockBean
    TaxRepository taxRepo;

    @MockBean
    ProcessingFeeRepository proFeeRepo;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldGetInvoices() throws Exception {

        // ACT
        mockMvc.perform(get("/invoices"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetInvoiceById() throws Exception {

        // ACT
        mockMvc.perform(get("/invoices/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetInvoiceByCustomerName() throws Exception {

        // ACT
        mockMvc.perform(get("/invoices/invoice/Josh"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateInvoice() throws Exception {

        // ARRANGE
        Invoice invoice = new Invoice();
        invoice.setCity("New York");
        invoice.setQuantity(5);
        invoice.setName("Josh");
        invoice.setItemId(1);
        invoice.setItemType("type");
        invoice.setState("NY");
        invoice.setProcessingFee(new BigDecimal("1.99"));
        invoice.setTax(new BigDecimal("12.99"));
        invoice.setStreet("fake st");
        invoice.setSubtotal(new BigDecimal("100.45"));
        invoice.setTotal(new BigDecimal("113.15"));
        invoice.setZipCode("11111");


        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(invoice);

        // ACT

        mockMvc.perform(
                        post("/invoices")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());


    }



}