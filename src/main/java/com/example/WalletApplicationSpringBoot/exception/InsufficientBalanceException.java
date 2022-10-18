package com.example.WalletApplicationSpringBoot.exception;

public class InsufficientBalanceException extends Exception{
    public InsufficientBalanceException(String s){
        super(s);
    }
}
