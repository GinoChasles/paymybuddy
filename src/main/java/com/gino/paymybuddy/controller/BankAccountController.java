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

/**
 * The type Bank account controller.
 */
@RestController
@RequestMapping("bankAccount")
public class BankAccountController {
  private final BankAccountService bankAccountService;
  private final UserService userService;
  private final LoadingUser loadingUser;

  /**
   * Instantiates a new Bank account controller.
   *
   * @param bankAccountServiceParam the bank account service param
   * @param userServiceParam        the user service param
   * @param loadingUserParam        the loading user param
   */
  public BankAccountController(
      final BankAccountService bankAccountServiceParam,
      final UserService userServiceParam,
      final LoadingUser loadingUserParam) {
    bankAccountService = bankAccountServiceParam;
    userService = userServiceParam;
    loadingUser = loadingUserParam;
  }

  /**
   * Delete bank account model and view.
   *
   * @param id the id
   * @return the model and view
   */
  @DeleteMapping("/{id}")
  public ModelAndView deleteBankAccount(@PathVariable(value = "id") final int id) {
    ModelAndView mav = new ModelAndView("redirect:/user/profile");

    bankAccountService.delete(id);

    return mav;
  }

  /**
   * Add bank account model and view.
   *
   * @param accountParam the account param
   * @return the model and view
   */
  @PostMapping
  public ModelAndView addBankAccount(@ModelAttribute("bankAccount") final Account accountParam) {
    ModelAndView mav = new ModelAndView("redirect:/user/profile");
    int idUserLog = loadingUser.getUserLogId();
    accountParam.setUser(userService.findById(idUserLog).get());
    bankAccountService.insert(accountParam);
    mav.addObject("addBank", accountParam);
    return mav;
  }

  /**
   * Transfer to bank account model and view.
   *
   * @param idAccount the id account
   * @param amount    the amount
   * @return the model and view
   * @throws Exception the exception
   */
  @PostMapping("/transferIn")
  public ModelAndView transferToBankAccount(@RequestParam(value = "idAccount") final int idAccount,
                                            @RequestParam(value = "amount") final double amount)
      throws Exception {
    ModelAndView mav = new ModelAndView("redirect:/user/profile");
    LoadingUser loadingUserLocal = new LoadingUser(userService);
    int idUserLog = loadingUserLocal.getUserLogId();

    bankAccountService.transferToBankAccount(idUserLog, idAccount, amount);
    return mav;
  }

  /**
   * Transfer to amount model and view.
   *
   * @param idAccount the id account
   * @param amount    the amount
   * @return the model and view
   * @throws Exception the exception
   */
  @PostMapping("/transferOut")
  public ModelAndView transferToAmount(@RequestParam(value = "idAccount") final int idAccount,
                                       @RequestParam(value = "amount") final double amount)
      throws Exception {
    ModelAndView mav = new ModelAndView("redirect:/user/profile");
    LoadingUser loadingUserLocal = new LoadingUser(userService);
    int idUserLog = loadingUserLocal.getUserLogId();

    bankAccountService.transferToUserAccount(idUserLog, idAccount, amount);
    return mav;
  }
}
