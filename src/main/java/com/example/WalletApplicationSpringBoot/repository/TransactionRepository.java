package com.example.WalletApplicationSpringBoot.repository;

import com.example.WalletApplicationSpringBoot.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    @Query(
            value = "select * from transaction where wallet_no = ?1",
            nativeQuery = true
    )
    List<Transaction> findAllByWalletNo(Long walletNo);
}
