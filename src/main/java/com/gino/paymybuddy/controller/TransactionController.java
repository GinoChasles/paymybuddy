package com.gino.paymybuddy.controller;

import com.gino.paymybuddy.dto.TransactionDTO;
import com.gino.paymybuddy.model.Transaction;
import com.gino.paymybuddy.model.User;
import com.gino.paymybuddy.service.TransactionServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("transfer")
public class TransactionController {
  final private TransactionServiceImpl transactionService;

  TransactionController(final TransactionServiceImpl transactionServiceParam) {
    transactionService = transactionServiceParam;
  }

  @GetMapping(value = "/")
  public ModelAndView getTransactions(User userParam) {
    ModelAndView mav = new ModelAndView("transfer");
    mav.addObject("transactions", transactionService.findAll());
    mav.addObject("amount", userParam.getAccount());

    mav.addObject("friendList", userParam.getFriends());
    return mav;
  }
  @PostMapping(value = "/saveTransaction")
  public ResponseEntity<TransactionDTO> saveTransaction(@RequestParam(value = "description") String description,
                                                        @RequestParam(value = "amount") double amount,
                                                        @RequestParam(value = "idEmitter") int idEmitter,
                                                        @RequestParam(value = "idReceiver") int idReceiver) {

    return ResponseEntity.ok(transactionService.createTransaction(idEmitter,idReceiver,description,amount));
  }
}
