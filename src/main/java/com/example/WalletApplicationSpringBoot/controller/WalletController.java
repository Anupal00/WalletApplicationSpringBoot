package com.example.WalletApplicationSpringBoot.controller;

import com.example.WalletApplicationSpringBoot.entity.Wallet;
import com.example.WalletApplicationSpringBoot.exception.PasswordException;
import com.example.WalletApplicationSpringBoot.exception.UserNameException;
import com.example.WalletApplicationSpringBoot.service.WalletService;
import model.TransactionModel;
import model.WalletVerify;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/wallet/")
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/createAccount")
    public Wallet saveWallet(@RequestBody Wallet wallet) throws UserNameException, PasswordException {
        return walletService.saveWallet(wallet);
    }

    @PostMapping("/accountDetails")
    public Wallet getWallet(@RequestBody WalletVerify walletVerify){
        return walletService.getWallet(walletVerify);
    }

    @PostMapping("/checkBalance")
    public Double getBalance(@RequestBody WalletVerify walletVerify){
        return walletService.getBalance(walletVerify);
    }

    @PostMapping("/deposit")
    public String depositAmount(@RequestBody TransactionModel transactionModel){
        return walletService.depositAmount(transactionModel);
    }

}
