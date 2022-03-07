package com.gino.paymybuddy.controller;

import com.gino.paymybuddy.dto.TransactionDTO;
import com.gino.paymybuddy.model.Transaction;
import com.gino.paymybuddy.model.User;
import com.gino.paymybuddy.service.TransactionService;
import com.gino.paymybuddy.service.UserService;
import com.gino.paymybuddy.utils.Constante;
import com.gino.paymybuddy.utils.LoadingUser;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("transfer")
public class TransactionController {
  private final     Logger loggerLocal = (Logger) LoggerFactory.getLogger(this.getClass());
  final private TransactionService transactionService;
  final private UserService userService;


  TransactionController(final TransactionService transactionServiceParam,
                        final UserService userServiceParam) {
    transactionService = transactionServiceParam;
    userService = userServiceParam;
  }

  @GetMapping
  public ModelAndView getTransactions(HttpServletRequest request) {
    ModelAndView mav = new ModelAndView("transfer");
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
    User userLog = userService.findById(loadingUserLocal.getUserLogId()).get();
    double amountMax = userLog.getAccountBalance() - (userLog.getAccountBalance() * Constante.COMMISSION_POURCENTAGE / 100);
    mav.addObject("transactions", transactionService.findAllByReceiverId(idUserLog, PageRequest.of(page, size)));
    mav.addObject("amountMax", amountMax);
    mav.addObject("friendList", userService.findAllFriendsByIdUser(loadingUserLocal.getUserLogId()));
    mav.addObject("postTransaction", new TransactionDTO());

    return mav;
  }
  @PostMapping(value = "/saveTransaction")
  public ModelAndView saveTransaction(@ModelAttribute TransactionDTO transactionParam) {
    ModelAndView mav = new ModelAndView("redirect:/transfer");
    mav.addObject("postTransaction", transactionParam);
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentPrincipalName = authentication.getName();
    Optional<User> userOptionalLocal = userService.findUserByEmail(currentPrincipalName);
    if (userOptionalLocal.isPresent()){
      int idUserLog = userOptionalLocal.get().getIdUser();
    transactionService.createTransaction(idUserLog, transactionParam.getConnection(), transactionParam.getDescription(),
        transactionParam.getAmount());
    }
    return mav;
  }
}
