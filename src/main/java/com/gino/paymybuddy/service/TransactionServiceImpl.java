package com.gino.paymybuddy.service;

import com.gino.paymybuddy.model.Transaction;
import com.gino.paymybuddy.repository.TransactionRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService{

  private final TransactionRepository transactionRepository;

  public TransactionServiceImpl(
      final TransactionRepository transactionRepositoryParam) {
    transactionRepository = transactionRepositoryParam;
  }


  @Override
  public Optional<Transaction> findById(final int id) {
    return transactionRepository.findById(id);
  }

  @Override
  public Transaction insert(final Transaction transactionParam) {
    return transactionRepository.save(transactionParam);
  }

  @Override
  public Page<Transaction> findAllByEmitterId(final int id, Pageable pageableParam) {
    return transactionRepository.findAllByEmitter(id, pageableParam);
  }

  @Override
  public Page<Transaction> findAllByReceiverId(final int id, Pageable pageableParam) {
    return transactionRepository.findAllByReceiver(id, pageableParam);
  }
}
