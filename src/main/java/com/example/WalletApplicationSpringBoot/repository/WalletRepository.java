package com.example.WalletApplicationSpringBoot.repository;

import com.example.WalletApplicationSpringBoot.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet,Long>{
    Optional<Wallet> findByUserNameAndPassword(String userName, String password);

    @Transactional
    @Modifying
    @Query(
            value = "update wallet set balance = ?1 where user_name=?2 and password = ?3",
            nativeQuery = true
    )
    int updateWalletByBalance(Double amount,String userName, String password);
}
