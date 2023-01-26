package com.sir.wallet.controller;

import com.sir.wallet.model.Transaction;
import com.sir.wallet.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transactions")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
       Transaction transCreated=transactionService.createTransaction(transaction);
        return new ResponseEntity<Transaction>(transCreated, HttpStatus.CREATED);

    }
    @DeleteMapping("/transactions")
    public ResponseEntity<Void> deleteTransaction( @RequestBody Transaction transaction )  {

        transactionService.deleteTransaction(transaction);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/transactions")
    public ResponseEntity<Transaction> updateTransaction(Transaction transaction) {
        return ResponseEntity.ok(transactionService.updateTransaction(transaction));
    }

    @GetMapping("/transactions")
    public ResponseEntity<Iterable<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Transaction>> getTransactionById( @PathVariable("id") Long id) {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }



}
