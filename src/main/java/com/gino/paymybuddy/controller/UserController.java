package com.gino.paymybuddy.controller;

import com.gino.paymybuddy.model.Account;
import com.gino.paymybuddy.model.User;
import com.gino.paymybuddy.service.BankAccountService;
import com.gino.paymybuddy.service.TransactionService;
import com.gino.paymybuddy.service.UserService;
import com.gino.paymybuddy.utils.LoadingUser;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.PageRequest;
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
  private final TransactionService transactionService;
  private final BankAccountService bankAccountService;

  public UserController(final UserService userServiceParam,
                        final TransactionService transactionServiceParam,
                        final BankAccountService bankAccountServiceParam) {
    userService = userServiceParam;
    transactionService = transactionServiceParam;
    bankAccountService = bankAccountServiceParam;
  }

  @PostMapping("/addFriend")
  public ModelAndView saveFriend(@RequestParam(value = "email") String email)
      throws Exception {
    LoadingUser loadingUserLocal = new LoadingUser(userService);
    userService.addFriend(email, loadingUserLocal.getUserLogId());

    return new ModelAndView("redirect:/transfer");
  }

  @GetMapping("/profile")
  public ModelAndView profile(HttpServletRequest request) {
    ModelAndView mav = new ModelAndView("/profile");

    int page = 0;
    int size = 3;

    if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
      page = Integer.parseInt(request.getParameter("page")) - 1;
    }

    if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
      size = Integer.parseInt(request.getParameter("size"));
    }

    LoadingUser loadingUserLocal = new LoadingUser(userService);
    int idUserLog = loadingUserLocal.getUserLogId();
    mav.addObject("transactions", transactionService.findAllByEmitterId(idUserLog, PageRequest.of(page, size)));
    mav.addObject("listBankAccount", bankAccountService.findAllByUserId(idUserLog ));
    mav.addObject("addBank", new Account());
    return mav;
  }



  @GetMapping
  public List<User> getAll() {
    return userService.findAll();
  }
}
