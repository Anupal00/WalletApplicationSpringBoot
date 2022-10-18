package com.example.WalletApplicationSpringBoot.controller;

import com.example.WalletApplicationSpringBoot.entity.Wallet;
import com.example.WalletApplicationSpringBoot.service.WalletService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/wallet/")
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public Wallet createWallet(@RequestBody Wallet wallet){
        return walletService.createWallet(wallet);
    }

}
