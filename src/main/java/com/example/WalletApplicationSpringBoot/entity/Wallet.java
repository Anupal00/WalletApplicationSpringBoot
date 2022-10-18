package com.example.WalletApplicationSpringBoot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wallet {

    @Id
    @SequenceGenerator(
            name = "account_sequence",
            sequenceName = "account_sequence"
    )
    @GeneratedValue(
            generator = "account_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long walletAccountNo;
    private String firstName;
    private String lastName;
    private String address;
    private Long balance;
}
