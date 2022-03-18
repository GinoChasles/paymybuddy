package com.gino.paymybuddy.service;

import com.gino.paymybuddy.model.User;
import com.gino.paymybuddy.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class CustomUserDetailsServiceTest {

  @InjectMocks
  private CustomUserDetailsService customUserDetailsService;

  @Mock
  private static UserRepository userRepository;

  private User user;

  @BeforeEach
  public void setUp() {
    user = new User(1,"username1", "password", "username1@gmail.com", 50);

  }

  @Test
  public void loadUserByUsernameTest() {
    when(userRepository.findUserByEmail(user.getEmail())).thenReturn(
        java.util.Optional.ofNullable(user));

    UserDetails userDetailsLocal = customUserDetailsService.loadUserByUsername(user.getEmail());

    assertThat(userDetailsLocal).isNotNull();
    assertThat(userDetailsLocal.getUsername()).isEqualTo(user.getEmail());
  }

  @Test
  public void loadUserByUsernameTest_whenUserDoesNotExist() {

    assertThrows(UsernameNotFoundException.class, () -> customUserDetailsService.loadUserByUsername(user.getEmail()));

  }
}
