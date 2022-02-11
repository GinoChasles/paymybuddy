package com.gino.paymybuddy.controller;

import com.gino.paymybuddy.model.User;
import com.gino.paymybuddy.service.UserService;
import com.gino.paymybuddy.utils.LoadingUser;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("user")
public class UserController {

  private final UserService userService;

  public UserController(final UserService userServiceParam) {
    userService = userServiceParam;
  }

  @PostMapping("/addFriend")
  public ModelAndView saveFriend(@RequestParam(value = "email") String email, User userParam)
      throws Exception {
    LoadingUser loadingUserLocal = new LoadingUser(userService);
    userService.addFriend(email, loadingUserLocal.getUserLogId());
    ModelAndView mav = new ModelAndView("redirect:/transfer");

    return mav;
  }



  @GetMapping
  public List<User> getAll() {
    return userService.findAll();
  }
}
