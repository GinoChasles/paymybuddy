package com.gino.paymybuddy.service;

import com.gino.paymybuddy.model.Role;
import com.gino.paymybuddy.model.User;
import com.gino.paymybuddy.repository.RoleRepository;
import com.gino.paymybuddy.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  public UserServiceImpl(final UserRepository userRepositoryParam,
                         final RoleRepository roleRepositoryParam) {
    userRepository = userRepositoryParam;
    roleRepository = roleRepositoryParam;
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
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String encodedPassword = encoder.encode(userParam.getPassword());
    userParam.setPassword(encodedPassword);
    Optional<Role> roleUser = roleRepository.findById(1);
    if (roleUser.isPresent()) {
      Role roleLocal = roleUser.get();
      List<Role> roleListLocal = userParam.getRoles();
      roleListLocal.add(roleLocal);
      return userRepository.save(userParam);
    }
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
      userLocal.setFriends(userParam.getFriends());
      userLocal.setAccount(userParam.getAccount());
      userLocal.setAccountBalance(userParam.getAccountBalance());
      userLocal.setTransactionsEmit(userParam.getTransactionsEmit());
      userLocal.setTransactionsReceiver(userParam.getTransactionsReceiver());
      userLocal.setRoles(userParam.getRoles());
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

  @Override
  public void addFriend(final String email, final int id) throws Exception {
    Optional<User> userOptionalLocal = this.findById(id);
    Optional<User> optionalUserLocal = this.findUserByEmail(email);

    if (optionalUserLocal.isPresent()) {
      List<User> friendList = userOptionalLocal.get().getFriends();
      if (!friendList.contains(optionalUserLocal)) {
        friendList.add(optionalUserLocal.get());
        userOptionalLocal.get().setFriends(friendList);
        this.update(id, userOptionalLocal.get());
      } else {
        throw new Exception("This person is already in you're friend's list !");
      }

    } else {
      throw new Exception("This person doesn't exist !");
    }
  }

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }
}
