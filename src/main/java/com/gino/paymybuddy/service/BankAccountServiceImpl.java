package com.gino.paymybuddy.service;

import com.gino.paymybuddy.model.Account;
import com.gino.paymybuddy.model.User;
import com.gino.paymybuddy.repository.BankAccountRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class BankAccountServiceImpl implements BankAccountService {

  private final BankAccountRepository bankAccountRepository;
  private final UserService userService;

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
    bankAccountRepository.deleteById(id);
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
  public void transferToUserAccount(final int idUser, final int idAccount, final double value) throws Exception{
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
  public void transferToBankAccount(final int idUser, final int idAccount, final double value)
      throws Exception {
    User userLocal = userService.findById(idUser).get();
    Account accountLocal = bankAccountRepository.findById(idAccount).get();
    double amount = userLocal.getAccountBalance();
    if (value <= amount) {
      accountLocal.setAmount(amount + value);
      userLocal.setAccountBalance(userLocal.getAccountBalance() - value);

      userService.update(idUser, userLocal);
      this.update(idAccount, accountLocal);
    } else {
      throw new Exception("You can't transfer more that you have !");
    }
  }
}
