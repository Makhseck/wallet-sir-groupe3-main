package com.sir.wallet.services;

import com.sir.wallet.model.Transaction;
import com.sir.wallet.model.Wallet;
import com.sir.wallet.repository.TransactionRepository;
import com.sir.wallet.repository.WalletRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    TransactionRepository transactionRepository;
    WalletRepository walletRepository;
    public TransactionServiceImpl(TransactionRepository transactionRepository,WalletRepository walletRepository){
        this.transactionRepository =transactionRepository;
        this.walletRepository=walletRepository;
    }


    @Override
    public Transaction createTransaction(Transaction transaction) {

        Optional<Wallet> wallet= walletRepository.findById(transaction.getWalletId());
        if(wallet.isPresent()){
            transaction.setWallet(wallet.get());
            return transactionRepository.save(transaction);
        }
        return null;
    }

    @Override
    public Optional<Transaction> getTransactionById(Long id) {
        return this.transactionRepository.findById(id)
        ;
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        Optional<Wallet> wallet= walletRepository.findById(transaction.getWalletId());
        if(wallet.isPresent()){
            transaction.setWallet(wallet.get());
            return transactionRepository.save(transaction);
        }
        return transaction;
    }

    @Override
    public void deleteTransaction(Transaction transaction) {
            this.transactionRepository.delete(transaction);
    }

    @Override
    public Iterable<Transaction> getAllTransactions() {

        return this.transactionRepository.findAll();
    }
}
