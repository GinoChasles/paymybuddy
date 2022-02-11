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
    if (userOptionalLocal.isPresent()) {
      return userOptionalLocal.get().getIdUser();
    } else return 0;
  }
}
