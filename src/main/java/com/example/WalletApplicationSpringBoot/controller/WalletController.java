package com.example.WalletApplicationSpringBoot.controller;

import com.example.WalletApplicationSpringBoot.service.WalletService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/wallet/")
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

}
