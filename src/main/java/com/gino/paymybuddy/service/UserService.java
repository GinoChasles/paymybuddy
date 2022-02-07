package com.gino.paymybuddy.service;

import com.gino.paymybuddy.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

  Optional<User> findById(final int id);
  Optional<User> findUserByEmail(final String email);
  User insert(final User userParam);
  User update(final int id, final User userParam);
  void delete(final int id);
  List<User> findAllFriendsByIdUser(final int id);
  void addFriend(final String email, final int id) throws Exception;
  List<User> findAll();
}
