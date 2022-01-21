package com.gino.paymybuddy.repository;

import com.gino.paymybuddy.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<Account, Integer> {
}
