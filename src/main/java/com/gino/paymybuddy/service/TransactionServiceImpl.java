package com.gino.paymybuddy.service;

import com.gino.paymybuddy.model.Transaction;
import com.gino.paymybuddy.model.User;
import com.gino.paymybuddy.repository.TransactionRepository;
import com.gino.paymybuddy.repository.UserRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService{

  private final TransactionRepository transactionRepository;
  private final UserRepository userRepository;

  public TransactionServiceImpl(
      final TransactionRepository transactionRepositoryParam,
      final UserRepository userRepositoryParam) {
    transactionRepository = transactionRepositoryParam;
    userRepository = userRepositoryParam;
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

  @Override
  public Transaction createTransaction(final int idEmitter, final int idReceiver,
                                       final String description, final double amount) {
    User receiver = new User();
    User emitter = new User();
    Optional<User> optionalReceiver = userRepository.findById(idReceiver);
    Optional<User> optionalEmitter = userRepository.findById(idEmitter);
    if (optionalReceiver.isPresent() && optionalEmitter.isPresent()) {
      receiver = optionalReceiver.get();
      emitter = optionalEmitter.get();
    } else {
      return null;
    }

    Transaction result = new Transaction();
    result.setAmount(amount);
    result.setDescription(description);
    result.setEmitter(emitter);
    result.setReceiver(receiver);
    transactionRepository.save(result);
    return result;
  }
}
