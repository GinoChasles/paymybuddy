package com.gino.paymybuddy.controller;

import com.gino.paymybuddy.model.User;
import com.gino.paymybuddy.service.UserService;
import java.util.Collection;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

  private final UserService userService;

  public LoginController(final UserService userServiceParam) {
    userService = userServiceParam;
  }


  @RequestMapping("/admin")
  @RolesAllowed("ADMIN")
  public String getAdmin() {
    return "Admin !";
  }

  @GetMapping("/home")
  public ModelAndView getTransactions(User userParam) {
    ModelAndView mav = new ModelAndView("home");
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentPrincipalName = authentication.getName();
    Collection<? extends GrantedAuthority> test = authentication.getAuthorities();
    mav.addObject("authorities", test);
    mav.addObject("currentPrincipalName", currentPrincipalName);

    return mav;
  }


  @GetMapping("/register")
  public ModelAndView getRegister() {
    ModelAndView mav = new ModelAndView("register");

    return mav;
  }

  @PostMapping("/register")
  public ModelAndView register(@Valid final User userParam, final Model modelParam) throws Exception {
    ModelAndView mav = new ModelAndView("login");

    modelParam.addAttribute("user", userParam);
    userService.insert(userParam);
    return mav;
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
