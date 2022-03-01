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

@RestController
public class LoginController {

  private final UserService userService;
  private final AppPropertiesExt appPropertiesExt;


  public LoginController(final UserService userServiceParam,
                         final AppPropertiesExt appPropertiesExtParam) {
    userService = userServiceParam;
    appPropertiesExt = appPropertiesExtParam;
  }


  @RequestMapping("/admin")
  @RolesAllowed("ADMIN")
  public String getAdmin() {
    return "Admin !";
  }

  @GetMapping("/home")
  public ModelAndView getTransactions(User userParam) {
    ModelAndView mav = new ModelAndView("home");
    LoadingUser loadingUserLocal = new LoadingUser(userService);

    String currentPrincipalName = userService.findUserByEmail(loadingUserLocal.getUserLogName()).get().getUsername();

    mav.addObject("currentPrincipalName", currentPrincipalName);

    return mav;
  }


  @GetMapping("/register")
  public ModelAndView getRegister() {
    ModelAndView mav = new ModelAndView("register");
//    mav.addObject("userAlreadyExist");
    return mav;
  }

  @PostMapping("/register")
  public ModelAndView register(@Valid final User userParam){
    ModelAndView mav = new ModelAndView("login");
//try {

    userService.insert(userParam);
//} catch (Exception e) {
//  mav.addObject("userAlreadyExist", this.appPropertiesExt.getError().getUserAlreadyExist());
//  mav.setViewName("redirect:/register");
//}
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
