package com.gino.paymybuddy.service;

import com.gino.paymybuddy.model.Account;
import java.util.List;
import java.util.Optional;

public interface BankAccountService {

  Optional<Account> findById(final int id);
  Account insert(final Account accountParam);
  void delete(final int id);
  Account update(final int id, final Account accountParam);
  List<Account> findAllByUserId(final int id);
}
