package com.gino.paymybuddy.service;

import com.gino.paymybuddy.model.User;
import com.gino.paymybuddy.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

  private final UserRepository userRepository;

  public UserServiceImpl(final UserRepository userRepositoryParam) {
    userRepository = userRepositoryParam;
  }

  @Override
  public Optional<User> findById(final int id) {
    return userRepository.findById(id);
  }

  @Override
  public Optional<User> findUserByEmail(final String email) {
    return userRepository.findUserByEmail(email);
  }

  @Override
  public User insert(final User userParam) {
    return userRepository.save(userParam);
  }

  @Override
  public User update(final int id, final User userParam) {
    Optional<User> optionalUserLocal = this.findById(id);
    if (optionalUserLocal.isPresent()) {
      User userLocal = optionalUserLocal.get();
      userLocal.setUsername(userParam.getUsername());
      userLocal.setEmail(userParam.getEmail());
      userLocal.setAccountBalance(userParam.getAccountBalance());
      return userRepository.save(userLocal);
    } else {
      return null;
    }
  }

  @Override
  public void delete(final int id) {
    userRepository.deleteById(id);
  }

  @Override
  public List<User> findAllFriendsByIdUser(final int id) {
//    Optional<User> optionalUserLocal = this.findById(id);
//    if (optionalUserLocal.isPresent()) {
//      User userLocal = optionalUserLocal.get();
//      userLocal.setFriends(userRepository.findAllFriendsByIdUser(id));
//      return userLocal.getFriends();
//    } else {
//      return null;
//    }
    return userRepository.findAllFriendsByIdUser(id);
  }
}
