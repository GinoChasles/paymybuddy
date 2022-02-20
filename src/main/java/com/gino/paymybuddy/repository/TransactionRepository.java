package com.gino.paymybuddy.repository;

import com.gino.paymybuddy.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

  Page<Transaction> findAllByEmitter_IdUser(final int idUser, Pageable pageableParam);
  Page<Transaction> findAllByReceiver_IdUser(final int idUser, Pageable pageableParam);

}
