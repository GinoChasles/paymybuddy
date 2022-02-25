package com.gino.paymybuddy.service;

import com.gino.paymybuddy.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

  Optional<User> findById(final int id);
  Optional<User> findUserByEmail(final String email);
  User insert(final User userParam) throws Exception;
  User update(final int id, final User userParam);
  void delete(final int id);
  List<User> findAllFriendsByIdUser(final int id);
  Page<User> findAllFriendsByIdUserPage(final int id, Pageable pageableParam);

  void addFriend(final String email, final int id) throws Exception;
  List<User> findAll();
}
