package com.gino.paymybuddy.service;

import com.gino.paymybuddy.exceptions.UserAlreadyExist;
import com.gino.paymybuddy.exceptions.UserAlreadyInFriendList;
import com.gino.paymybuddy.exceptions.UserDoesNotExist;
import com.gino.paymybuddy.model.Role;
import com.gino.paymybuddy.model.User;
import com.gino.paymybuddy.repository.RoleRepository;
import com.gino.paymybuddy.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * The type User service test.
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  @InjectMocks
  private UserServiceImpl userService;

  @Mock
  private UserRepository userRepository;

  @Mock
  private RoleRepository roleRepository;

  private User user1;
  private User user2;
  private User user3;
  private static List<User> userList = new ArrayList<>();
  private static List<User> user1FriendList = new ArrayList<>();
  private static Role roleUser1;


  /**
   * Sets up.
   */
  @BeforeEach
  void setUp() {
    user1 = new User(1,"username1", "password", "username1@gmail.com", 50);
    user2 = new User(2,"username2", "password", "username2@gmail.com", 150);
    user3 = new User(3,"username3", "password", "username3@gmail.com", 100);
    userList.add(user1);
    userList.add(user2);
    userList.add(user3);
    user1FriendList.add(user2);
    user1FriendList.add(user3);
    roleUser1 = new Role("ROLE_USER", "userRole");
  }

  /**
   * Find by id test.
   */
  @Test
  public void findByIdTest() {
    when(userRepository.findById(anyInt())).thenReturn(Optional.of(user1));

    Optional<User> userLocal = userService.findById(1);
    User userTest = new User();
    if (userLocal.isPresent()) {
      userTest = userLocal.get();
    }

    assertThat(userTest.getUsername()).isEqualTo(user1.getUsername());
    verify(userRepository, times(1)).findById(anyInt());
  }

  /**
   * Find user by email test.
   */
  @Test
  public void findUserByEmailTest() {
    when(userRepository.findUserByEmail(anyString())).thenReturn(Optional.of(user1));

    Optional<User> userLocal = userService.findUserByEmail(user1.getEmail());
    User userTest = new User();
    if (userLocal.isPresent()) {
      userTest = userLocal.get();
    }

    assertThat(userTest.getUsername()).isEqualTo(user1.getUsername());
    assertThat(userTest.getEmail()).isEqualTo(user1.getEmail());
    verify(userRepository, times(1)).findUserByEmail(anyString());
  }

  /**
   * Insert test.
   */
  @Test
  public void insertTest() {
    when(roleRepository.findById(anyInt())).thenReturn(Optional.ofNullable(roleUser1));
    when(userRepository.save(any(User.class))).thenReturn(user1);
    User userLocal = userService.insert(user1);

    assertThat(userLocal.getIdUser()).isEqualTo(user1.getIdUser());
    assertThat(userLocal.getUsername()).isEqualTo(user1.getUsername());
    assertThat(userLocal.getPassword()).isEqualTo(user1.getPassword());
    assertThat(userLocal.getRoles().get(0).getRole()).isEqualTo(roleUser1.getRole());
  }

  /**
   * Insert test user already exist.
   */
  @Test
  public void insertTest_UserAlreadyExist() {
    when(roleRepository.findById(anyInt())).thenReturn(Optional.ofNullable(roleUser1));
    when(userRepository.findUserByEmail(anyString())).thenReturn(Optional.ofNullable(user1));

    assertThrows(UserAlreadyExist.class,() -> userService.insert(user1));
  }

  /**
   * Insert test when user role is not present.
   */
  @Test
  public void insertTest_whenUserRoleIsNotPresent() {
    when(userRepository.save(any(User.class))).thenReturn(user1);

    User userLocal = userService.insert(user1);

    assertThat(userLocal.getIdUser()).isEqualTo(user1.getIdUser());
    assertThat(userLocal.getUsername()).isEqualTo(user1.getUsername());
    assertThat(userLocal.getPassword()).isEqualTo(user1.getPassword());
    assertThat(userLocal.getRoles().size()).isEqualTo(0);

  }

  /**
   * Update test.
   */
  @Test
  public void updateTest() {
    when(userRepository.findById(anyInt())).thenReturn(Optional.ofNullable(user1));
    User userTest = user1;
      userTest.setUsername("NewUsername");
      userService.update(user1.getIdUser(), userTest);
      assertThat(user1.getUsername()).isEqualTo("NewUsername");

  }

  /**
   * Update test when user does not exist.
   */
  @Test
  public void updateTest_whenUserDoesNotExist() {
    when(userService.findById(anyInt())).thenReturn(Optional.empty());

    assertThrows(UserDoesNotExist.class, () -> userService.update(user1.getIdUser(),user1));

  }

  /**
   * Delete test.
   */
  @Test
  public void deleteTest() {
    ArgumentCaptor<User> argumentCaptorLocal = ArgumentCaptor.forClass(User.class);
    when(userRepository.findById(anyInt())).thenReturn(Optional.ofNullable(user1));

      userService.delete(user1.getIdUser());
      verify(userRepository, times(1)).delete(argumentCaptorLocal.capture());

  }

  /**
   * Find all friends by id user test.
   */
  @Test
  public void findAllFriendsByIdUserTest() {
    when(userRepository.findFriends(user1.getIdUser())).thenReturn(user1FriendList);
    List<User> userListTest = userService.findAllFriendsByIdUser(user1.getIdUser());
    assertThat(userListTest.size()).isEqualTo(user1FriendList.size());
    assertThat(userListTest.get(0).getUsername()).isEqualTo(user1FriendList.get(0).getUsername());
  }

  /**
   * Find all friends by id user page test.
   */
  @Test
  public void findAllFriendsByIdUserPageTest() {
    Pageable paging = PageRequest.of(0, 2);
    Page<User> pro = new PageImpl<User>(user1FriendList);
    when(userRepository.findFriendsPage(Mockito.anyInt(), Mockito.any())).thenReturn(pro);
    Page<User> userPageTest = userService.findAllFriendsByIdUserPage(1, paging);

    assertThat(userPageTest.get().findFirst().get().getUsername()).isEqualTo("username2");
  }

  /**
   * Add friend test.
   */
  @Test
  public void addFriendTest() {
    when(userService.findById(anyInt())).thenReturn(Optional.of(user2));
    when(userService.findUserByEmail(user3.getEmail())).thenReturn(Optional.of(user3));

    userService.addFriend(user3.getEmail(), user2.getIdUser());

    assertThat(user2.getFriends().size()).isEqualTo(1);
  }

  /**
   * Find all test.
   */
  @Test
  public void findAllTest() {
    when(userRepository.findAll()).thenReturn(userList);

    List<User> userListLocal = userService.findAll();
    assertThat(userListLocal.size()).isEqualTo(userList.size());
  }

  /**
   * Add friend test when friend already exist.
   */
  @Test
  public void addFriendTest_whenFriendAlreadyExist() {
    user1.setFriends(user1FriendList);
    when(userService.findById(anyInt())).thenReturn(Optional.of(user1));
    when(userService.findUserByEmail(user2.getEmail())).thenReturn(Optional.of(user2));


    assertThrows(UserAlreadyInFriendList.class, () -> userService.addFriend(user2.getEmail(), user1.getIdUser()));
  }

  /**
   * Add friend test when friend does not exist.
   */
  @Test
  public void addFriendTest_whenFriendDoesNotExist() {
    when(userService.findById(anyInt())).thenReturn(Optional.of(user2));
    when(userService.findUserByEmail(user3.getEmail())).thenReturn(Optional.empty());

    assertThrows(UserDoesNotExist.class, () -> userService.addFriend(user3.getEmail(), user2.getIdUser()));
  }

}
