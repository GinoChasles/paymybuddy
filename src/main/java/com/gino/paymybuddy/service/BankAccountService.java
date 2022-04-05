package com.gino.paymybuddy.service;

import com.gino.paymybuddy.model.Account;
import java.util.List;
import java.util.Optional;

/**
 * The interface Bank account service.
 */
public interface BankAccountService {

  /**
   * Find by id optional.
   *
   * @param id the id
   * @return the optional
   */
  Optional<Account> findById(final int id);

  /**
   * Insert account.
   *
   * @param accountParam the account param
   * @return the account
   */
  Account insert(final Account accountParam);

  /**
   * Delete.
   *
   * @param id the id
   */
  void delete(final int id);

  /**
   * Update account.
   *
   * @param id           the id
   * @param accountParam the account param
   * @return the account
   */
  Account update(final int id, final Account accountParam);

  /**
   * Find all by user id list.
   *
   * @param id the id
   * @return the list
   */
  List<Account> findAllByUserId(final int id);

  /**
   * Transfer to user account.
   *
   * @param idUser    the id user
   * @param idAccount the id account
   * @param value     the value
   * @throws Exception the exception
   */
  void transferToUserAccount(final int idUser, final int idAccount, final double value) throws Exception;

  /**
   * Transfer to bank account.
   *
   * @param idUser    the id user
   * @param idAccount the id account
   * @param value     the value
   * @throws Exception the exception
   */
  void transferToBankAccount(final int idUser, final int idAccount, final double value) throws Exception;
}
