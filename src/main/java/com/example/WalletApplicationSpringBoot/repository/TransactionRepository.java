package com.example.WalletApplicationSpringBoot.repository;

import com.example.WalletApplicationSpringBoot.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
