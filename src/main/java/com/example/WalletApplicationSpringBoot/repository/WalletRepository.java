package com.example.WalletApplicationSpringBoot.repository;

import com.example.WalletApplicationSpringBoot.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet,Long>{
    Optional<Wallet> findByUserNameAndPassword(String userName, String password);
}
