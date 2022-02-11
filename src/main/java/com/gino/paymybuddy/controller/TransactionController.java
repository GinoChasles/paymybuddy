package com.gino.paymybuddy.controller;

import com.gino.paymybuddy.model.User;
import com.gino.paymybuddy.service.TransactionService;
import com.gino.paymybuddy.service.UserService;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("transfer")
public class TransactionController {
  final private TransactionService transactionService;
  final private UserService userService;

  TransactionController(final TransactionService transactionServiceParam,
                        final UserService userServiceParam) {
    transactionService = transactionServiceParam;
    userService = userServiceParam;
  }

  @GetMapping
  public ModelAndView getTransactions(User userParam) {
    ModelAndView mav = new ModelAndView("transfer");
    mav.addObject("transactions", transactionService.findAll());
    mav.addObject("amount", userParam.getAccount());
    mav.addObject("friendList", userParam.getFriends());
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentPrincipalName = authentication.getName();
    mav.addObject("currentPrincipalName", currentPrincipalName);

    return mav;
  }
  @PostMapping(value = "/saveTransaction")
  public ModelAndView saveTransaction(@RequestParam(value = "description") String description,
                                                        @RequestParam(value = "amount") double amount,
                                                        @RequestParam(value = "idReceiver") int idReceiver) {
    ModelAndView mav = new ModelAndView("transfer");
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentPrincipalName = authentication.getName();
    Optional<User> userOptionalLocal = userService.findUserByEmail(currentPrincipalName);
    if (userOptionalLocal.isPresent()){
      int idUserLog = userOptionalLocal.get().getIdUser();
    transactionService.createTransaction(idUserLog,idReceiver,description,amount);
    }
    return mav;
  }
}
