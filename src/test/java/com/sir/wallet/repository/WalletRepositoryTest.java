package com.sir.wallet.repository;
import com.sir.wallet.model.Wallet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WalletRepositoryTest {
    @Autowired
    WalletRepository walletRepository;

    @Test
    void save() {
        //Given

        Wallet wallet = new Wallet(1L, "My Wallet", 100);

        //When
        Wallet responseWallet = walletRepository.save(wallet);

        //Then
        assertNotNull(responseWallet);
        assertEquals(responseWallet.getName(), wallet.getName());

    }



    @Test
    void update() {
        //Given
        Wallet wallet = walletRepository.save(new Wallet(1L,"Compte1",10000));

        wallet.setBalance(15);
        wallet.setName("Compte épargne");

        //When
        Wallet walletUpdate = walletRepository.save(wallet);

        //Then
        assertNotNull(walletUpdate);
        assertEquals(walletUpdate.getName(), wallet.getName());
    }
    @Test
    void delete(){
        //Given
        Wallet wallet = walletRepository.save(new Wallet(1L,"Compte1",11000000));
        Long idwallet= wallet.getId();
        //when
        walletRepository.delete(wallet);

        //then
        assertEquals(walletRepository.findById(idwallet), Optional.empty());

    }

    // done
    @Test
    void findById(){
        //Given
        Wallet wallet =  walletRepository.save(new Wallet(1L,"Compte5",11000));
       Long idWallet = wallet.getId();
        //when
        Optional<Wallet> searchWallet = walletRepository.findById(idWallet);

        //then
        assertTrue(searchWallet.isPresent());
        assertEquals(searchWallet.get().getBalance(),wallet.getBalance());

    }

    // done
    @Test
    void findAll(){
        //Given
        List<Wallet> wallets= new ArrayList<>();


        wallets.add(walletRepository.save(new Wallet(1L,"Compte5",500000)));

        //when
        List<Wallet> searchall =walletRepository.findAll();

        //then
        assertNotNull(searchall);
        assertEquals(searchall.size(),wallets.size());

    }
}
