package com.example.WalletApplicationSpringBoot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @SequenceGenerator(
            name = "transaction_sequence",
            sequenceName = "transaction_sequence"
    )
    @GeneratedValue(
            generator = "transaction_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long transactionId;
    private Long amount;
    private Date date;
    private Time time;

}
