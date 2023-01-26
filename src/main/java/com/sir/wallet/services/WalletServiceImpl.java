package com.sir.wallet.services;

import com.sir.wallet.model.Wallet;
import com.sir.wallet.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {
    WalletRepository walletRepository;
    public WalletServiceImpl(WalletRepository  walletRepository) {
        this.walletRepository = walletRepository;
    }
    @Override
    public Optional<Wallet> getWalletById(Long id) {

        return this.walletRepository.findById(id);
    }

    @Override
    public Wallet saveWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet updateWallet(Long id, Wallet wallet) {
      Optional<Wallet> walletUpd= this.getWalletById(id);
      if(walletUpd.isPresent()){
         walletUpd.get().setName(wallet.getName());
         walletUpd.get().setBalance(wallet.getBalance());
         return this.walletRepository.save(walletUpd.get());
      }
       else
        return null;
    }

    @Override
    public void deleteWallet(Wallet wallet) {
            this.walletRepository.delete(wallet);
    }

    @Override
    public Iterable<Wallet> getAllWallets() {
        return this.walletRepository.findAll()
                ;
    }
}
