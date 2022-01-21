package com.gino.paymybuddy.service;

import com.gino.paymybuddy.model.Transaction;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionService {
  Optional<Transaction> findById(final int id);
  Transaction insert(Transaction transactionParam);
  Page<Transaction> findAllByEmitterId(final int id, Pageable pageableParam);
  Page<Transaction> findAllByReceiverId(final int id, Pageable pageableParam);
  Transaction createTransaction(final int idEmitter, final int idReceiver, final String description, final double amount);
}
