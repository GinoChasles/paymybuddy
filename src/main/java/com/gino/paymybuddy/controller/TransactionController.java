package com.gino.paymybuddy.controller;

import com.gino.paymybuddy.dto.TransactionDTO;
import com.gino.paymybuddy.model.User;
import com.gino.paymybuddy.service.TransactionService;
import com.gino.paymybuddy.service.UserService;
import com.gino.paymybuddy.utils.Constante;
import com.gino.paymybuddy.utils.LoadingUser;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * The type Transaction controller.
 */
@RestController
@RequestMapping("transfer")
public class TransactionController {
  private final     Logger loggerLocal = (Logger) LoggerFactory.getLogger(this.getClass());
  private final TransactionService transactionService;
  private final UserService userService;
  private final LoadingUser loadingUser;


  /**
   * Instantiates a new Transaction controller.
   *
   * @param transactionServiceParam the transaction service param
   * @param userServiceParam        the user service param
   * @param loadingUserParam        the loading user param
   */
  TransactionController(final TransactionService transactionServiceParam,
                        final UserService userServiceParam,
                        final LoadingUser loadingUserParam) {
    transactionService = transactionServiceParam;
    userService = userServiceParam;
    loadingUser = loadingUserParam;
  }

  /**
   * Gets transactions.
   *
   * @param request the request
   * @return the transactions
   */
  @GetMapping
  public ModelAndView getTransactions(final HttpServletRequest request) {
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

  /**
   * Save transaction model and view.
   *
   * @param transactionParam the transaction param
   * @return the model and view
   */
  @PostMapping(value = "/saveTransaction")
  public ModelAndView saveTransaction(@ModelAttribute final TransactionDTO transactionParam) {
    ModelAndView mav = new ModelAndView("redirect:/transfer");
    mav.addObject("postTransaction", transactionParam);

    transactionService.createTransaction(loadingUser.getUserLogId(), transactionParam.getConnection(), transactionParam.getDescription(),
        transactionParam.getAmount());

    return mav;
  }
}
