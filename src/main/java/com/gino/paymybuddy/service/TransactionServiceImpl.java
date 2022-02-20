package com.gino.paymybuddy.service;

import com.gino.paymybuddy.dto.TransactionDTO;
import com.gino.paymybuddy.model.Account;
import com.gino.paymybuddy.model.Commission;
import com.gino.paymybuddy.model.Enterprise;
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
  private final UserService userService;
  private final EnterpriseService enterpriseService;
  private final CommissionService commissionService;
  private final BankAccountService bankAccountService;

  public TransactionServiceImpl(
      final TransactionRepository transactionRepositoryParam,
      final UserService userServiceParam,
      final EnterpriseService enterpriseServiceParam,
      final CommissionService commissionServiceParam,
      final BankAccountService bankAccountServiceParam) {
    transactionRepository = transactionRepositoryParam;
    userService = userServiceParam;
    enterpriseService = enterpriseServiceParam;
    commissionService = commissionServiceParam;
    bankAccountService = bankAccountServiceParam;
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
    return transactionRepository.findAllByEmitter_IdUser(id, pageableParam);
  }

  @Override
  public Page<Transaction> findAllByReceiverId(final int id, Pageable pageableParam) {
    return transactionRepository.findAllByReceiver_IdUser(id, pageableParam);
  }

  @Override
  public Transaction createTransaction(final int idEmitter, final String connection,
                                          final String description, final double amount) {
    User receiver;
    User emitter;
    Optional<User> optionalReceiver =  userService.findUserByEmail(connection);
    Optional<User> optionalEmitter = userService.findById(idEmitter);
    double commission;

    if (optionalReceiver.isPresent() && optionalEmitter.isPresent()) {
      receiver = optionalReceiver.get();
      emitter = optionalEmitter.get();

      Transaction result = new Transaction();
      result.setAmount(amount);
      result.setDescription(description);
      result.setEmitter(emitter);
      result.setReceiver(receiver);
      transactionRepository.save(result);

      TransactionDTO transactionDTOLocal = new TransactionDTO();
      transactionDTOLocal.setConnection(receiver.getUsername());
      transactionDTOLocal.setDescription(description);
      transactionDTOLocal.setAmount(amount);

      Commission commissionLocal = new Commission();
      commissionLocal.setPourcentage(Constante.COMMISSION_POURCENTAGE);
      commissionLocal.setEnterprise(enterpriseService.findById(Constante.ENTERPRISE_ID).get());
      commissionLocal.setTransaction(result);
      commission = amount/100 * commissionLocal.getPourcentage();
      if (commission < 0.01) {
        commission = 0.01;
      }
      commissionLocal.setCommisssionCount(commission);
      commissionService.insert(commissionLocal);

      emitter.setAccountBalance(emitter.getAccountBalance() - amount - commission);
      receiver.setAccountBalance(receiver.getAccountBalance() + amount);


      Enterprise enterpriseLocal = enterpriseService.findById(Constante.ENTERPRISE_ID).get();
      Account enterpriseAccount = enterpriseLocal.getAccount();
      enterpriseAccount.setAmount(enterpriseAccount.getAmount() + commission);
      bankAccountService.update(enterpriseAccount.getIdAccount(), enterpriseAccount);

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
