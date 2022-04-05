package com.gino.paymybuddy.repository;

import com.gino.paymybuddy.model.Account;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Bank account repository.
 */
@Repository
public interface BankAccountRepository extends JpaRepository<Account, Integer> {
  /**
   * Find all by user id user list.
   *
   * @param id the id
   * @return the list
   */
  List<Account> findAllByUser_IdUser(final int id);
}
