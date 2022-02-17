package com.gino.paymybuddy.utils;

import com.gino.paymybuddy.model.User;
import com.gino.paymybuddy.service.UserService;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoadingUser {
  private final UserService userService;

  public LoadingUser(final UserService userServiceParam) {
    userService = userServiceParam;
  }

  public int getUserLogId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentPrincipalName = authentication.getName();
    Optional<User> userOptionalLocal = userService.findUserByEmail(currentPrincipalName);
    return userOptionalLocal.map(User::getIdUser).orElse(0);
  }

  public String getUserLogName() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication.getName();
  }
}
