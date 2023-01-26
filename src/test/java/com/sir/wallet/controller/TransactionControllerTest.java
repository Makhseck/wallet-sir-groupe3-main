package com.sir.wallet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sir.wallet.model.Transaction;
import com.sir.wallet.model.Wallet;
import com.sir.wallet.services.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TransactionControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    TransactionService transactionService;
    ObjectMapper objectMapper= new ObjectMapper();
    @Test
    void createTransaction() throws Exception {
        //Given
        Transaction transaction = new Transaction(1L,20000,"depot");
        when(transactionService.createTransaction(any())).thenReturn(transaction);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/transaction/transactions")
                .content(objectMapper.writeValueAsString(transaction))
                .contentType(MediaType.APPLICATION_JSON); // "application/json"


        //When
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        // Then
        assertTrue(response.getContentAsString().contains(transaction.getType()));

        assertEquals(HttpStatus.OK.value(), 200);
    }

    @Test
    void deleteTransaction() throws Exception {
        //Given
        Transaction transaction = new Transaction(1L,20000,"depot");

        doNothing().when(transactionService).deleteTransaction(transaction);
        when(transactionService.getTransactionById(any())).thenReturn(Optional.of(transaction));
        RequestBuilder request = MockMvcRequestBuilders
                .delete("/api/transaction/transactions")
                .content(objectMapper.writeValueAsString(transaction))
                .contentType(MediaType.APPLICATION_JSON); // "application/json"


        //When
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        // Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void updateTransaction() throws Exception{
        //Given
        Transaction transaction = new Transaction(1L,20000,"depot");
        when(transactionService.updateTransaction(any())).thenReturn(transaction);
        RequestBuilder request = MockMvcRequestBuilders
                .put("/api/transaction/transactions")
                .content(objectMapper.writeValueAsString(transaction))
                .contentType(MediaType.APPLICATION_JSON); // "application/json"


        //When
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        // Then
        assertTrue(response.getContentAsString().contains("2000"));

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void getAllTransactions() throws Exception {
        List<Transaction> transactions= new ArrayList<>();
        Wallet wallet = new Wallet(1L,"Compte2",1000000);
        //Wallet wallet = new Wallet(1L,"Compte5",500000);

        transactions.add(new Transaction(1L, 30000,"retrait"));
        transactions.add(new Transaction(1L, 100000,"depot"));

            //Given
        when(transactionService.getAllTransactions()).thenReturn(transactions);
            RequestBuilder request = MockMvcRequestBuilders
                    .get("/api/transaction/transactions")
                    .contentType(MediaType.APPLICATION_JSON); // "application/json"


            //When
            MvcResult mvcResult = mockMvc.perform(request).andReturn();
            MockHttpServletResponse response = mvcResult.getResponse();

            // Then
            assertEquals(HttpStatus.OK.value(), response.getStatus());


            assertTrue(response.getContentAsString().contains(objectMapper.writeValueAsString(transactions)));

    }

    @Test
    void getTransactionById() throws Exception {

        Transaction transaction = new Transaction(1L,20000,"depot");
    when(transactionService.getTransactionById(any())).thenReturn(Optional.of(transaction));
        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/transaction/1")
                .contentType(MediaType.APPLICATION_JSON);
        //When
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        // Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());


        assertTrue(response.getContentAsString().contains(objectMapper.writeValueAsString(transaction)));


    }
}