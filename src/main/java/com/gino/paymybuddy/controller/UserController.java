package com.gino.paymybuddy.controller;

import com.gino.paymybuddy.exceptions.UserAlreadyInFriendList;
import com.gino.paymybuddy.exceptions.UserDoesNotExist;
import com.gino.paymybuddy.model.Account;
import com.gino.paymybuddy.model.User;
import com.gino.paymybuddy.service.BankAccountService;
import com.gino.paymybuddy.service.TransactionService;
import com.gino.paymybuddy.service.UserService;
import com.gino.paymybuddy.utils.AppPropertiesExt;
import com.gino.paymybuddy.utils.Constante;
import com.gino.paymybuddy.utils.LoadingUser;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("user")
public class UserController {

  private final UserService userService;
  private final TransactionService transactionService;
  private final BankAccountService bankAccountService;
  private final AppPropertiesExt appPropertiesExt;

  public UserController(final UserService userServiceParam,
                        final TransactionService transactionServiceParam,
                        final BankAccountService bankAccountServiceParam,
                        final AppPropertiesExt appPropertiesExtParam) {
    userService = userServiceParam;
    transactionService = transactionServiceParam;
    bankAccountService = bankAccountServiceParam;
    appPropertiesExt = appPropertiesExtParam;
  }

  @PostMapping("/addFriend")
  public ModelAndView saveFriend(@RequestParam(value = "email") String email) {
    LoadingUser loadingUserLocal = new LoadingUser(userService);
    ModelAndView mav = new ModelAndView("redirect:/user/contact");
    try {

    userService.addFriend(email, loadingUserLocal.getUserLogId());
    } catch (UserAlreadyInFriendList|UserDoesNotExist e) {
      mav.addObject("userDoesNotExist", this.appPropertiesExt.getError().getUserDoesNotExist());
      mav.addObject("alreadyInFriendList", this.appPropertiesExt.getError().getAlreadyInFriendList());
    }

    return mav;
  }

  @GetMapping("/profile")
  public ModelAndView profile(HttpServletRequest request) {
    ModelAndView mav = new ModelAndView("/profile");

    int page = Constante.PAGE_NUMBER;
    int size = Constante.PAGE_SIZE;

    if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
      page = Integer.parseInt(request.getParameter("page")) - 1;
    }

    if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
      size = Integer.parseInt(request.getParameter("size"));
    }

    LoadingUser loadingUserLocal = new LoadingUser(userService);
    int idUserLog = loadingUserLocal.getUserLogId();
    mav.addObject("accountBalance", userService.findById(idUserLog).get().getAccountBalance());
    mav.addObject("transactions", transactionService.findAllByEmitterId(idUserLog, PageRequest.of(page, size)));
    mav.addObject("listBankAccount", bankAccountService.findAllByUserId(idUserLog ));
    mav.addObject("addBank", new Account());
    return mav;
  }



  @GetMapping
  public List<User> getAll() {
    return userService.findAll();
  }

  @GetMapping("/contact")
  public ModelAndView getContact(HttpServletRequest request) {
    ModelAndView mav = new ModelAndView("/contact");
    LoadingUser loadingUserLocal = new LoadingUser(userService);
    int page = Constante.PAGE_NUMBER;
    int size = Constante.PAGE_SIZE;

    if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
      page = Integer.parseInt(request.getParameter("page")) - 1;
    }

    if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
      size = Integer.parseInt(request.getParameter("size"));
    }
    int idUserLog = loadingUserLocal.getUserLogId();
    mav.addObject("friendsList", userService.findAllFriendsByIdUserPage(idUserLog, PageRequest.of(page, size)));
    mav.addObject("alreadyInFriendList");
    mav.addObject("userDoesNotExist");
//    mav.addObject("userDoesNotExist", this.appPropertiesExt.getError().getUserDoesNotExist());
//    mav.addObject("alreadyInFriendList", this.appPropertiesExt.getError().getAlreadyInFriendList());
    return mav;
  }
}
