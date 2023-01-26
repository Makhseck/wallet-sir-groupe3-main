package com.sir.wallet.repository;

import com.sir.wallet.model.Transaction;
import com.sir.wallet.model.Wallet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionRepositoryTest {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    WalletRepository walletRepository;


    @Test
    void save() {
        //Given
        Wallet wallet = new Wallet(1L,"Compte1",10000);
        walletRepository.save(wallet);
        Transaction trans = new Transaction(wallet.getId(),5000,"depot");

        //When
        Transaction transSave  = transactionRepository.save(trans);

        //Then
        assertNotNull(transSave);
        assertTrue(transSave.getId() > 0);
        assertEquals(transSave.getWallet(), trans.getWallet());
    }


    @Test
    void update() {
        //Given
        Wallet wallet = new Wallet(1L,"Compte1",10000);
        walletRepository.save(wallet);
        Transaction trans = transactionRepository.save(new Transaction(wallet.getId(),5000,"depot"));

        trans.setAmount(10000);
        trans.setType("retrait");

        //When
        Transaction transUpdate = transactionRepository.save(trans);

        //Then
        assertNotNull(transUpdate);
        assertEquals(transUpdate.getType(), trans.getType());
    }
    @Test
    void delete(){
        //Given
        Wallet wallet = new Wallet(1L,"Compte1",11000000);
        walletRepository.save(wallet);
        Transaction transaction = transactionRepository.save(new Transaction(wallet.getId(),10000000,"retrait"));
        Long idTrans = transaction.getId();
        //when
        transactionRepository.delete(transaction);

        //then
        assertEquals(transactionRepository.findById(idTrans), Optional.empty());

    }

    // done
    @Test
    void findById(){
        //Given
        Wallet wallet = new Wallet(1L,"Compte5",11000);
        walletRepository.save(wallet);
        Transaction transaction = transactionRepository.save(new Transaction(wallet.getId(),1000,"depot"));
        Long idTrans = transaction.getId();
        //when
        Optional<Transaction> searchTrans = transactionRepository.findById(idTrans);

        //then
        assertTrue(searchTrans.isPresent());
        assertEquals(searchTrans.get().getAmount(),transaction.getAmount());

    }

    // done
    @Test
    void findAll(){
        //Given
        List<Transaction> transactions= new ArrayList<>();
        Wallet wallet = new Wallet(1L,"Compte5",500000);
        walletRepository.save(wallet);

        transactions.add(transactionRepository.save(new Transaction(wallet.getId(), 15000,"retrait")));
        transactions.add(transactionRepository.save(new Transaction(wallet.getId(), 50000,"depot")));
        transactions.add(transactionRepository.save(new Transaction(wallet.getId(),1000000, "depot")));
        transactions.add(transactionRepository.save(new Transaction(wallet.getId(),400000, "retrait")));

        //when
        List<Transaction> searchall = transactionRepository.findAll();

        //then
        assertNotNull(searchall);
        assertEquals(searchall.size(),transactions.size());

    }
}