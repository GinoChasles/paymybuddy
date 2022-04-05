package com.gino.paymybuddy.utils;

import com.gino.paymybuddy.model.User;
import com.gino.paymybuddy.service.UserService;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * The type Loading user.
 */
@Service
public class LoadingUser {
  private final UserService userService;

  /**
   * Instantiates a new Loading user.
   *
   * @param userServiceParam the user service param
   */
  public LoadingUser(final UserService userServiceParam) {
    userService = userServiceParam;
  }

  /**
   * Gets user log id.
   *
   * @return the user log id
   */
  public int getUserLogId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentPrincipalName = authentication.getName();
    Optional<User> userOptionalLocal = userService.findUserByEmail(currentPrincipalName);
    return userOptionalLocal.map(User::getIdUser).orElse(0);
  }

  /**
   * Gets user log name.
   *
   * @return the user log name
   */
  public String getUserLogName() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication.getName();
  }
}
