package com.sir.wallet.controller;


import com.sir.wallet.model.Wallet;
import com.sir.wallet.services.WalletService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    private final WalletService walletService;
    private static Logger logger =  LogManager.getLogger(WalletController.class);


    public WalletController(WalletService walletService) {

        logger.info("WalletController constructor");
        this.walletService = walletService;
    }

    @GetMapping("/wallets/{id}")
    public Optional<Wallet> getWallet(@PathVariable long id) {

        return walletService.getWalletById(id);
    }

    @PutMapping("/wallets/{id}")
    public Wallet updateWallet(@PathVariable long id, @RequestBody Wallet wallet) {
        return walletService.updateWallet(id, wallet);
    }

    @PostMapping("/wallets")
    public ResponseEntity<Wallet> saveWallet(@RequestBody  Wallet wallet) {
        logger.info("===> savePerson");
        Wallet savedWallet = walletService.saveWallet(wallet);
        return  new ResponseEntity<>(savedWallet, HttpStatus.CREATED);
    }
    @DeleteMapping("/wallets/{id}")
    public ResponseEntity deleteWallet(Wallet wallet )  {

        walletService.deleteWallet(wallet);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/wallets")
    public ResponseEntity<Iterable<Wallet>> getAllWallets() {
        return ResponseEntity.ok(walletService.getAllWallets());
    }
}
