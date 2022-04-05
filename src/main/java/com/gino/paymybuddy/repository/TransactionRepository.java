package com.gino.paymybuddy.repository;

import com.gino.paymybuddy.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Transaction repository.
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

  /**
   * Find all by emitter id user page.
   *
   * @param idUser        the id user
   * @param pageableParam the pageable param
   * @return the page
   */
  Page<Transaction> findAllByEmitter_IdUser(final int idUser, Pageable pageableParam);

  /**
   * Find all by receiver id user page.
   *
   * @param idUser        the id user
   * @param pageableParam the pageable param
   * @return the page
   */
  Page<Transaction> findAllByReceiver_IdUser(final int idUser, Pageable pageableParam);

}
