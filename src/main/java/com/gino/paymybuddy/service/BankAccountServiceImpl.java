package com.gino.paymybuddy.service;

import com.gino.paymybuddy.model.Account;
import com.gino.paymybuddy.repository.BankAccountRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class BankAccountServiceImpl implements BankAccountService {

  private final BankAccountRepository bankAccountRepository;

  public BankAccountServiceImpl(
      final BankAccountRepository bankAccountRepositoryParam) {
    bankAccountRepository = bankAccountRepositoryParam;
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
}
