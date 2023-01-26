package com.sir.wallet.services;
import com.sir.wallet.model.Wallet;
import com.sir.wallet.repository.WalletRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class WalletServiceImplTest {
    @InjectMocks
    WalletServiceImpl walletService;

    @Mock
    WalletRepository walletRepository;
    @Test
    void getWalletById() {
        Wallet wallet  = new Wallet(1L,"Epargne",1000000);
        //Given

        when(walletRepository.findById(any())).thenReturn(Optional.of(wallet));

        //When
        Optional<Wallet> wallet1 = walletService.getWalletById(wallet.getId());

        //Then
        assertTrue(wallet1.isPresent());
        assertEquals(wallet1.get().getName(), wallet.getName());
        verify(walletRepository, times(1)).findById(wallet.getId());

    }

    @Test
    void saveWallet() {
        //Given

        Wallet wallet  = new Wallet(1L,"Epargne",1000000);
        when(walletRepository.save(any())).thenReturn(wallet);

        //When
        Wallet walletResponse = walletService.saveWallet(wallet);

        //Then
        assertEquals(1L, walletResponse.getId());
        verify(walletRepository, times(1)).save(wallet);
    }

    @Test
    void updateWallet() {
        //Given
        Wallet wallet  = new Wallet("Epargne",1000000);
        Long id=1L;
        when(walletRepository.save(any())).thenReturn(wallet);
        when(walletRepository.findById(id)).thenReturn(Optional.of(wallet));
        wallet.setName("Courant");
        wallet.setBalance(250000);
        //When
        Wallet walletUpd = walletService.updateWallet(id,wallet);

        //Then
        assertEquals("Courant", walletUpd.getName());
        assertEquals(wallet.getBalance(), walletUpd.getBalance());
        verify(walletRepository, times(1)).save(wallet);
    }

    @Test
    void deleteWallet() {
        //Given
        Wallet wallet  = new Wallet(1L,"Epargne",1000000);
        doNothing().when(walletRepository).delete(wallet);

        //When
        walletService.deleteWallet(wallet);

        //Then
        verify(walletRepository, times(1)).delete(wallet);
    }

    @Test
    void getAllWallets() {

        List<Wallet> wallets= new ArrayList<>();


        wallets.add(new Wallet(1L,"Compte5",500000));
        when(walletRepository.findAll()).thenReturn(wallets);

        //When

        Iterable<Wallet> wallets1= walletService.getAllWallets();

        //Then
        assertEquals(wallets,wallets1);
        verify(walletRepository, times(1)).findAll();
    }
}