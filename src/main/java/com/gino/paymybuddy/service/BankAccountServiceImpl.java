package com.gino.paymybuddy.service;

import com.gino.paymybuddy.model.Account;
import com.gino.paymybuddy.model.User;
import com.gino.paymybuddy.repository.BankAccountRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * The type Bank account service.
 */
@Service
public class BankAccountServiceImpl implements BankAccountService {

  private final BankAccountRepository bankAccountRepository;
  private final UserService userService;

  /**
   * Instantiates a new Bank account service.
   *
   * @param bankAccountRepositoryParam the bank account repository param
   * @param userServiceParam           the user service param
   */
  public BankAccountServiceImpl(
      final BankAccountRepository bankAccountRepositoryParam,
      final UserService userServiceParam) {
    bankAccountRepository = bankAccountRepositoryParam;
    userService = userServiceParam;
  }


  @Override
  public Optional<Account> findById(final int id) {
    return bankAccountRepository.findById(id);
  }

  @Override
  public Account insert(final Account accountParam) {
    return bankAccountRepository.save(accountParam);
  }

  @Override
  public void delete(final int id) {

    Optional<Account> accountLocal = this.findById(id);
    accountLocal.ifPresent(bankAccountRepository::delete);
  }

  @Override
  public Account update(final int id, final Account accountParam) {
    Optional<Account> optionalBankAccountLocal = this.findById(id);
    if (optionalBankAccountLocal.isPresent()) {
      Account accountToUpdateLocal = optionalBankAccountLocal.get();
      accountToUpdateLocal.setAccountnumber(accountParam.getAccountnumber());
      accountToUpdateLocal.setBic(accountParam.getBic());
      accountToUpdateLocal.setIban(accountParam.getIban());
      accountToUpdateLocal.setAmount(accountParam.getAmount());

      return bankAccountRepository.save(accountToUpdateLocal);
    } else {
      return null;
    }
  }

  @Override
  public List<Account> findAllByUserId(final int id) {
    return bankAccountRepository.findAllByUser_IdUser(id);
  }

  @Override
  @Transactional
  public void transferToUserAccount(final int idUser, final int idAccount, final double value) throws Exception {
    User userLocal = userService.findById(idUser).get();
    Account accountLocal = bankAccountRepository.findById(idAccount).get();
    double amount = accountLocal.getAmount();
    if (value <= amount) {
      accountLocal.setAmount(amount - value);
      userLocal.setAccountBalance(userLocal.getAccountBalance() + value);

      userService.update(idUser, userLocal);
      this.update(idAccount, accountLocal);
    } else {
      throw new Exception("You can't transfer more that you have !");
    }
  }

  @Override
  @Transactional
  public void transferToBankAccount(final int idUser, final int idAccount, final double value)
      throws Exception {
    User userLocal = userService.findById(idUser).get();
    Account accountLocal = bankAccountRepository.findById(idAccount).get();
    double amountUser = userLocal.getAccountBalance();
    double amountAccount = accountLocal.getAmount();
    if (value <= amountUser) {
      accountLocal.setAmount(amountAccount + value);
      userLocal.setAccountBalance(userLocal.getAccountBalance() - value);

      userService.update(idUser, userLocal);
      this.update(idAccount, accountLocal);
    } else {
      throw new Exception("You can't transfer more that you have !");
    }
  }
}
