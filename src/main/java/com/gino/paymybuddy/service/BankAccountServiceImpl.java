package com.gino.paymybuddy.service;

import com.gino.paymybuddy.model.BankAccount;
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
  public Optional<BankAccount> findById(final int id) {
    return bankAccountRepository.findById(id);
  }

  @Override
  public BankAccount insert(final BankAccount bankAccountParam) {
    return bankAccountRepository.save(bankAccountParam);
  }

  @Override
  public void delete(final int id) {
    bankAccountRepository.deleteById(id);
  }

  @Override
  public BankAccount update(final int id, final BankAccount bankAccountParam) {
    Optional<BankAccount> optionalBankAccountLocal = this.findById(id);
    if (optionalBankAccountLocal.isPresent()) {
      BankAccount bankAccountToUpdate = optionalBankAccountLocal.get();
      bankAccountToUpdate.setAccountNumber(bankAccountParam.getAccountNumber());
      bankAccountToUpdate.setBic(bankAccountParam.getBic());
      bankAccountToUpdate.setIban(bankAccountParam.getIban());
      bankAccountToUpdate.setKey(bankAccountParam.getKey());
      bankAccountToUpdate.setAmount(bankAccountParam.getAmount());

      return bankAccountRepository.save(bankAccountToUpdate);
    } else {
      return null;
    }
  }
}
