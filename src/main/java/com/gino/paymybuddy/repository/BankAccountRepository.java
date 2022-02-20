package com.gino.paymybuddy.repository;

import com.gino.paymybuddy.model.Account;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<Account, Integer> {
  List<Account> findAllByUser_IdUser(final int id);
}
