package com.gino.paymybuddy.controller;

import com.gino.paymybuddy.model.User;
import com.gino.paymybuddy.service.UserService;
import com.gino.paymybuddy.utils.AppPropertiesExt;
import com.gino.paymybuddy.utils.LoadingUser;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * The type Login controller.
 */
@RestController
public class LoginController {

  private final UserService userService;
  private final AppPropertiesExt appPropertiesExt;


  /**
   * Instantiates a new Login controller.
   *
   * @param userServiceParam      the user service param
   * @param appPropertiesExtParam the app properties ext param
   */
  public LoginController(final UserService userServiceParam,
                         final AppPropertiesExt appPropertiesExtParam) {
    userService = userServiceParam;
    appPropertiesExt = appPropertiesExtParam;
  }


  /**
   * Gets admin.
   *
   * @return the admin
   */
  @RequestMapping("/admin")
  @RolesAllowed("ADMIN")
  public String getAdmin() {
    return "Admin !";
  }

  /**
   * Gets transactions.
   *
   * @param userParam the user param
   * @return the transactions
   */
  @GetMapping("/home")
  public ModelAndView getTransactions(final User userParam) {
    ModelAndView mav = new ModelAndView("home");
    LoadingUser loadingUserLocal = new LoadingUser(userService);

    String currentPrincipalName = userService.findUserByEmail(loadingUserLocal.getUserLogName()).get().getUsername();

    mav.addObject("currentPrincipalName", currentPrincipalName);

    return mav;
  }


  /**
   * Gets register.
   *
   * @return the register
   */
  @GetMapping("/register")
  public ModelAndView getRegister() {
    ModelAndView mav = new ModelAndView("register");
    return mav;
  }

  /**
   * Register model and view.
   *
   * @param userParam the user param
   * @return the model and view
   */
  @PostMapping("/register")
  public ModelAndView register(@Valid final User userParam) {
    ModelAndView mav = new ModelAndView("login");
    userService.insert(userParam);
    return mav;
  }
}
