package com.gino.paymybuddy.controller;

import com.gino.paymybuddy.model.Account;
import com.gino.paymybuddy.service.BankAccountService;
import com.gino.paymybuddy.service.UserService;
import com.gino.paymybuddy.utils.LoadingUser;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("bankAccount")
public class BankAccountController {
  final private BankAccountService bankAccountService;
  final private UserService userService;
  final private LoadingUser loadingUser;

  public BankAccountController(
      final BankAccountService bankAccountServiceParam,
      final UserService userServiceParam,
      final LoadingUser loadingUserParam) {
    bankAccountService = bankAccountServiceParam;
    userService = userServiceParam;
    loadingUser = loadingUserParam;
  }

  @DeleteMapping("/{id}")
  public ModelAndView deleteBankAccount(@PathVariable(value = "id") final int id) {
    ModelAndView mav = new ModelAndView("redirect:/user/profile");

      bankAccountService.delete(id);

    return mav;
  }

  @PostMapping
  public ModelAndView addBankAccount(@ModelAttribute("bancAccount") Account accountParam) {
    ModelAndView mav = new ModelAndView("redirect:/user/profile");
    int idUserLog = loadingUser.getUserLogId();
    accountParam.setUser(userService.findById(idUserLog).get());
    bankAccountService.insert(accountParam);
    mav.addObject("addBank", accountParam);
    return mav;
  }

  @PostMapping("/transferIn")
  public ModelAndView transferToBankAccount(@RequestParam(value = "idAccount") final int idAccount, @RequestParam(value = "amount") final double amount)
      throws Exception {
    ModelAndView mav = new ModelAndView("redirect:/user/profile");
    LoadingUser loadingUserLocal = new LoadingUser(userService);
    int idUserLog = loadingUserLocal.getUserLogId();

    bankAccountService.transferToBankAccount(idUserLog, idAccount, amount);
    return mav;
  }

  @PostMapping("/transferOut")
  public ModelAndView transferToAmount(@RequestParam(value = "idAccount") final int idAccount, @RequestParam(value = "amount") final double amount)
      throws Exception {
    ModelAndView mav = new ModelAndView("redirect:/user/profile");
    LoadingUser loadingUserLocal = new LoadingUser(userService);
    int idUserLog = loadingUserLocal.getUserLogId();

    bankAccountService.transferToUserAccount(idUserLog, idAccount, amount);
    return mav;
  }
}
