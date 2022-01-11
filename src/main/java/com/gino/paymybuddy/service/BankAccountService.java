package com.gino.paymybuddy.service;

import com.gino.paymybuddy.model.BankAccount;
import java.util.Optional;

public interface BankAccountService {

  Optional<BankAccount> findById(final int id);
  BankAccount insert(final BankAccount bankAccountParam);
  void delete(final int id);
  BankAccount update(final int id, final BankAccount bankAccountParam);
}
