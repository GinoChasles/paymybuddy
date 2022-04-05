package com.gino.paymybuddy.service;

import com.gino.paymybuddy.dto.TransactionDTO;
import com.gino.paymybuddy.model.Transaction;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * The interface Transaction service.
 */
public interface TransactionService {
  /**
   * Find by id optional.
   *
   * @param id the id
   * @return the optional
   */
  Optional<Transaction> findById(final int id);

  /**
   * Insert transaction.
   *
   * @param transactionParam the transaction param
   * @return the transaction
   */
  Transaction insert(Transaction transactionParam);

  /**
   * Find all by emitter id page.
   *
   * @param id            the id
   * @param pageableParam the pageable param
   * @return the page
   */
  Page<Transaction> findAllByEmitterId(final int id, Pageable pageableParam);

  /**
   * Find all by receiver id page.
   *
   * @param id            the id
   * @param pageableParam the pageable param
   * @return the page
   */
  Page<Transaction> findAllByReceiverId(final int id, Pageable pageableParam);

  /**
   * Create transaction transaction.
   *
   * @param idEmitter   the id emitter
   * @param connection  the connection
   * @param description the description
   * @param amount      the amount
   * @return the transaction
   */
  Transaction createTransaction(final int idEmitter, final String connection, final String description, final double amount);

  /**
   * Find all list.
   *
   * @return the list
   */
  List<Transaction> findAll();
}
