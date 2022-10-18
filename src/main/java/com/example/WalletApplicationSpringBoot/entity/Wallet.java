package com.example.WalletApplicationSpringBoot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wallet {

    @Id
    @SequenceGenerator(
            name = "wallet_sequence",
            sequenceName = "wallet_sequence"
    )
    @GeneratedValue(
            generator = "wallet_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long walletNo;
    private String firstName;
    private String lastName;
    private String address;
    private Double balance;
    @Column(
            nullable = false
    )
    private String userName;
    @Column(
            nullable = false
    )
    private String password;
//    @OneToMany(
//            cascade = CascadeType.ALL
//    )
//    @JoinColumn(
//            name = "wallet_no",
//            referencedColumnName = "walletNo"
//    )
//    private List<Transaction> transactionList;
}
