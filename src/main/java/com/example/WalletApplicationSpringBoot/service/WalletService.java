package com.example.WalletApplicationSpringBoot.service;

import com.example.WalletApplicationSpringBoot.entity.Transaction;
import com.example.WalletApplicationSpringBoot.entity.Wallet;
import com.example.WalletApplicationSpringBoot.exception.InsufficientBalanceException;
import com.example.WalletApplicationSpringBoot.exception.PasswordException;
import com.example.WalletApplicationSpringBoot.exception.UserNameException;
import com.example.WalletApplicationSpringBoot.repository.TransactionRepository;
import com.example.WalletApplicationSpringBoot.repository.WalletRepository;
import model.TransactionModel;
import model.WalletVerify;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class WalletService {
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public WalletService(WalletRepository walletRepository, TransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    public Wallet saveWallet(Wallet wallet) throws UserNameException, PasswordException {
        if(wallet.getUserName()==null){
            throw new UserNameException("Username Required");
        }
        else if(wallet.getPassword()==null){
            throw new PasswordException("Password Required");
        }
        return walletRepository.save(wallet);
    }

    public Wallet getWallet(WalletVerify walletVerify) {
        return walletRepository.findByUserNameAndPassword(walletVerify.getUserName(), walletVerify.getPassword())
                .orElseThrow(()->new IllegalStateException("Not found"));
    }

    public Double getBalance(WalletVerify walletVerify) {
        return walletRepository.findByUserNameAndPassword(walletVerify.getUserName(), walletVerify.getPassword())
                .orElseThrow(()->new IllegalStateException("Not found")).getBalance();
    }

    public String depositAmount(TransactionModel transactionModel) {
        Wallet wallet = walletRepository.findByUserNameAndPassword(transactionModel.getUserName(), transactionModel.getPassword())
                .orElseThrow(()->new IllegalStateException("Not found"));
        Double balance = wallet.getBalance();
        balance = balance + transactionModel.getAmount();
        int status  = walletRepository.updateWalletByBalance(balance, transactionModel.getUserName(), transactionModel.getPassword());
        if(status==1){
            Transaction transaction = Transaction.builder()
                    .deposit(transactionModel.getAmount())
                    .balance(balance)
                    .date(Date.valueOf(LocalDate.now()))
                    .time(Time.valueOf(LocalTime.now()))
                    .status("Success")
                    .wallet(wallet)
                    .build();
            transactionRepository.save(transaction);
            return "Success";
        }
        else{
            Transaction transaction = Transaction.builder()
                    .deposit(transactionModel.getAmount())
                    .date(Date.valueOf(LocalDate.now()))
                    .time(Time.valueOf(LocalTime.now()))
                    .status("Failed")
                    .wallet(wallet)
                    .build();
            transactionRepository.save(transaction);
            return "Failed";
        }
    }

    public List<Transaction> showTransactionList(WalletVerify walletVerify) {
        Wallet wallet = walletRepository.findByUserNameAndPassword(walletVerify.getUserName(), walletVerify.getPassword())
                .orElseThrow(()->new IllegalStateException("Not found"));
        System.out.println(wallet);
        return transactionRepository.findAllByWalletNo(wallet.getWalletNo());
    }

    public String withdrawalAmount(TransactionModel transactionModel) throws InsufficientBalanceException{
        Wallet wallet = walletRepository.findByUserNameAndPassword(transactionModel.getUserName(), transactionModel.getPassword())
                .orElseThrow(()->new IllegalStateException("Not found"));
        Double balance = wallet.getBalance();
        if(balance < transactionModel.getAmount()){
            Transaction transaction = Transaction.builder()
                    .wallet(wallet)
                    .withdrawal(transactionModel.getAmount())
                    .balance(balance)
                    .date(Date.valueOf(LocalDate.now()))
                    .time(Time.valueOf(LocalTime.now()))
                    .status("Failed")
                    .build();
            transactionRepository.save(transaction);
            throw new InsufficientBalanceException("Insufficient Balance");
        }
        balance = balance - transactionModel.getAmount();
        walletRepository.updateWalletByBalance(balance, transactionModel.getUserName(), transactionModel.getPassword());
        Transaction transaction = Transaction.builder()
                .wallet(wallet)
                .withdrawal(transactionModel.getAmount())
                .balance(balance)
                .date(Date.valueOf(LocalDate.now()))
                .time(Time.valueOf(LocalTime.now()))
                .status("Success")
                .build();
        transactionRepository.save(transaction);
        return "Success";
    }
}
