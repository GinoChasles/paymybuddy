package com.gino.paymybuddy.service;

import com.gino.paymybuddy.dto.TransactionDTO;
import com.gino.paymybuddy.model.Commission;
import com.gino.paymybuddy.model.Transaction;
import com.gino.paymybuddy.model.User;
import com.gino.paymybuddy.repository.TransactionRepository;
import com.gino.paymybuddy.utils.Constante;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService{

  private final TransactionRepository transactionRepository;
  private final UserServiceImpl userService;
  private final EnterpriseServiceImpl enterpriseService;
  private final CommissionServiceImpl commissionService;

  public TransactionServiceImpl(
      final TransactionRepository transactionRepositoryParam,
      final UserServiceImpl userServiceParam,
      final EnterpriseServiceImpl enterpriseServiceParam,
      final CommissionServiceImpl commissionServiceParam) {
    transactionRepository = transactionRepositoryParam;
    userService = userServiceParam;
    enterpriseService = enterpriseServiceParam;
    commissionService = commissionServiceParam;
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
  public TransactionDTO createTransaction(final int idEmitter, final int idReceiver,
                                          final String description, final double amount) {
    User receiver;
    User emitter;
    Optional<User> optionalReceiver =  userService.findById(idReceiver);
    Optional<User> optionalEmitter = userService.findById(idEmitter);
    double commission;

    if (optionalReceiver.isPresent() && optionalEmitter.isPresent()) {
      receiver = optionalReceiver.get();
      emitter = optionalEmitter.get();

      Transaction transactionLocal = new Transaction();
      transactionLocal.setAmount(amount);
      transactionLocal.setDescription(description);
      transactionLocal.setEmitter(emitter);
      transactionLocal.setReceiver(receiver);
      transactionRepository.save(transactionLocal);

      TransactionDTO result = new TransactionDTO();
      result.setConnection(emitter.getUsername());
      result.setDescription(description);
      result.setAmount(amount);

      Commission commissionLocal = new Commission();
      commissionLocal.setPourcentage(Constante.COMMISSION_POURCENTAGE);
      commissionLocal.setEnterprise(enterpriseService.findById(Constante.ENTERPRISE_ID).get());
      commissionLocal.setTransaction(transactionLocal);
      commission = amount/100 * commissionLocal.getPourcentage();
      if (commission < 0.01) {
        commission = 0.01;
      }
      commissionLocal.setCommisssionCount(commission);
      commissionService.insert(commissionLocal);

      emitter.setAccountBalance(emitter.getAccountBalance() - amount - commission);
      List<Transaction> transactionListEmit = emitter.getTransactionsEmit();
      transactionListEmit.add(transactionLocal);
      emitter.setTransactionsEmit(transactionListEmit);

      List<Transaction> transactionListReceive = receiver.getTransactionsReceiver();
      transactionListReceive.add(transactionLocal);
      receiver.setTransactionsReceiver(transactionListReceive);
      receiver.setAccountBalance(receiver.getAccountBalance() + amount);

      userService.update(emitter.getIdUser(), emitter);
      userService.update(receiver.getIdUser(), receiver);

      return result;
    } else {
      return null;
    }
  }

  @Override
  public List<Transaction> findAll() {
    return transactionRepository.findAll();
  }
}
