package com.gino.paymybuddy.controller;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

  @RequestMapping("/user")
  @RolesAllowed("USER")
  public String getUser() {
    return "Welcome user";
  }


  @RequestMapping("/admin")
  @RolesAllowed("ADMIN")
  public String getAdmin() {
    return "Admin !";
  }

//  @RequestMapping(value="/logout", method = RequestMethod.GET)
//  public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
//    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//    if (auth != null){
//      new SecurityContextLogoutHandler().logout(request, response, auth);
////      logger.info("logout ok");
//    }
//    return "login";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
//  }


}
