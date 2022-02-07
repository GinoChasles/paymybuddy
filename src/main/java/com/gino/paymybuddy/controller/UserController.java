package com.gino.paymybuddy.controller;

import com.gino.paymybuddy.model.User;
import com.gino.paymybuddy.service.UserService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

  private final UserService userService;

  public UserController(final UserService userServiceParam) {
    userService = userServiceParam;
  }

  @PostMapping("/addFriend")
  public String saveFriend(@RequestParam(value = "email") String email, User userParam)
      throws Exception {
    userService.addFriend(email, userParam.getIdUser());
    return "add-connection";
  }

  @PostMapping("/register")
  public String register(@RequestBody final User userParam) {
//  public ResponseEntity<User> register(@RequestBody final User userParam) {
//    return ResponseEntity.ok().body(userService.insert(userParam));
    userService.insert(userParam);
    return "redirect:/login";
  }

  @GetMapping
  public List<User> getAll() {
    return userService.findAll();
  }
}
