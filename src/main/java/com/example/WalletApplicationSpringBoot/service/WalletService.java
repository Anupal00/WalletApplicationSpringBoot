package com.example.WalletApplicationSpringBoot.service;

import com.example.WalletApplicationSpringBoot.entity.Transaction;
import com.example.WalletApplicationSpringBoot.entity.Wallet;
import com.example.WalletApplicationSpringBoot.repository.TransactionRepository;
import com.example.WalletApplicationSpringBoot.repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public WalletService(WalletRepository walletRepository, TransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    public Wallet createWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }
}
