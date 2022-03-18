package com.gino.paymybuddy.service;

import com.gino.paymybuddy.exceptions.UserAlreadyExist;
import com.gino.paymybuddy.exceptions.UserAlreadyInFriendList;
import com.gino.paymybuddy.exceptions.UserDoesNotExist;
import com.gino.paymybuddy.model.Role;
import com.gino.paymybuddy.model.User;
import com.gino.paymybuddy.repository.RoleRepository;
import com.gino.paymybuddy.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
  @Transactional
  public User insert(final User userParam) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String encodedPassword = encoder.encode(userParam.getPassword());
    userParam.setPassword(encodedPassword);
    Optional<Role> roleUser = roleRepository.findById(1);
    Optional<User> userOptionalLocal = this.findUserByEmail(userParam.getEmail());
    if (userOptionalLocal.isEmpty()) {
      if (roleUser.isPresent()) {
        List<Role> roleListLocal = userParam.getRoles();
        roleListLocal.add(roleUser.get());
        userParam.setRoles(roleListLocal);
        return userRepository.save(userParam);
      }
      return userRepository.save(userParam);
    } else {
      throw new UserAlreadyExist("A user with this email is already register !");
    }
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
      throw new UserDoesNotExist("user doesn't exist");
    }
  }

  @Override
  public void delete(final int id) {
    Optional<User> optionalUserLocal = this.findById(id);
    optionalUserLocal.ifPresent(userRepository::delete);
  }

  @Override
  public List<User> findAllFriendsByIdUser(final int id) {
    return userRepository.findFriends(id);
  }

  @Override
  public Page<User> findAllFriendsByIdUserPage(final int id, final Pageable pageableParam) {
    return userRepository.findFriendsPage(id, pageableParam);
  }

  @Override
  public void addFriend(final String email, final int id) {
    Optional<User> userOptionalLocal = this.findById(id);
    Optional<User> optionalUserLocal = this.findUserByEmail(email);

    if (optionalUserLocal.isPresent()) {
      List<User> friendList = userOptionalLocal.get().getFriends();
      if (!friendList.contains(optionalUserLocal.get())) {
        friendList.add(optionalUserLocal.get());
        userOptionalLocal.get().setFriends(friendList);
        this.update(id, userOptionalLocal.get());
      } else {
        throw new UserAlreadyInFriendList("This person is already in you're friend's list !");
      }

    } else {
      throw new UserDoesNotExist("This person doesn't exist !");
    }
  }

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }
}
