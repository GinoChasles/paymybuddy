package com.gino.paymybuddy.service;

import com.gino.paymybuddy.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * The interface User service.
 */
public interface UserService {

  /**
   * Find by id optional.
   *
   * @param id the id
   * @return the optional
   */
  Optional<User> findById(final int id);

  /**
   * Find user by email optional.
   *
   * @param email the email
   * @return the optional
   */
  Optional<User> findUserByEmail(final String email);

  /**
   * Insert user.
   *
   * @param userParam the user param
   * @return the user
   */
  User insert(final User userParam);

  /**
   * Update user.
   *
   * @param id        the id
   * @param userParam the user param
   * @return the user
   */
  User update(final int id, final User userParam);

  /**
   * Delete.
   *
   * @param id the id
   */
  void delete(final int id);

  /**
   * Find all friends by id user list.
   *
   * @param id the id
   * @return the list
   */
  List<User> findAllFriendsByIdUser(final int id);

  /**
   * Find all friends by id user page page.
   *
   * @param id            the id
   * @param pageableParam the pageable param
   * @return the page
   */
  Page<User> findAllFriendsByIdUserPage(final int id, Pageable pageableParam);

  /**
   * Add friend.
   *
   * @param email the email
   * @param id    the id
   */
  void addFriend(final String email, final int id);

  /**
   * Find all list.
   *
   * @return the list
   */
  List<User> findAll();
}
