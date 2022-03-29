package com.gino.paymybuddy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gino.paymybuddy.PaymybuddyApplication;
import com.gino.paymybuddy.model.Account;
import com.gino.paymybuddy.model.User;
import com.gino.paymybuddy.service.BankAccountServiceImpl;
import com.gino.paymybuddy.service.UserServiceImpl;
import com.gino.paymybuddy.utils.LoadingUser;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;



@SpringBootTest(classes = PaymybuddyApplication.class)
@AutoConfigureMockMvc
public class BankAccountControllerIT {

  @Inject
  MockMvc mockMvc;

  @Autowired
  ObjectMapper mapper;

  @Autowired
  BankAccountServiceImpl bankAccountService;

//  @Mock
//  UserServiceImpl userService;

//  @MockBean
//  LoadingUser loadingUser;
//
//  @Autowired
//  BankAccountController bankAccountController;
  private Account account1;
  private Account account2;
  private User user1;
  private User user2;

  @BeforeEach
  public void setUp() {
    account1 = new Account(12345, "test1", "testAccountNumber1", 500);
    account2 = new Account(12345, "test2", "testAccountNumber2", 520);
    user1 = new User(1,"user1", "testtest", "email@teste.com", 50);
    user2 = new User(2,"user2", "testtest", "email@teste.com", 30);
    List<Account> accountListLocal = new ArrayList<>();
    accountListLocal.add(account1);
    user1.setAccount(accountListLocal);
  }

  @Test
  @WithMockUser(username = "user1", password = "pwd", roles = "USER")
  public void deleteBankAccountTest_whenBankAccountExistAndUserLog() throws Exception {
    mockMvc.perform(delete("/bankAccount/1")).andExpect(redirectedUrl("/user/profile"));
  }

  @Test
  public void deleteBankAccountTest_whenBankAccountExistAndUserNotLog() throws Exception {
    mockMvc.perform(delete("/bankAccount/1")).andExpect(status().is4xxClientError());
  }

  @Test
  public void addBankAccountTest_whenUserLog_Exception() {

    org.assertj.core.api.Assertions.assertThatThrownBy(() -> mockMvc.perform(MockMvcRequestBuilders.post("/bankAccount/")
        .with(user("email@teste.com").password("testtest").roles("USER"))).andExpect(status().isOk())).hasCause(new DataIntegrityViolationException("could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement"));

  }

  @Test
  public void addBankAccountTest_whenUserLog() throws Exception {
    String json = mapper.writeValueAsString(account1);

    mockMvc.perform(MockMvcRequestBuilders.post("/bankAccount/")
        .with(user("email@teste.com").password("testtest").roles("USER"))
//            .contentType(MediaType.APPLICATION_JSON)
            .flashAttr("iban",account1.getIban()));
//            .content(json));
//        .andExpect(view().name("redirect:/user/profile"));


  }

  @Test
  public void transferToBankAccountTest() throws Exception {
    mockMvc.perform(post("/bankAccount/transferIn")
            .with(user("email@teste.com").password("testtest").roles("USER"))
            .param("idAccount", String.valueOf(account1.getIdAccount()))
            .param("amount", "20")
        )
        .andExpect(view().name("redirect:/user/profile"));
    verify(bankAccountService).transferToBankAccount(user1.getIdUser(),account1.getIdAccount(),20);
  }

  @Test
  public void transferToAmountTest() throws Exception {
    mockMvc.perform(post("/bankAccount/transferOut")
            .with(user("email@teste.com").password("testtest").roles("USER"))
            .param("idAccount", String.valueOf(account1.getIdAccount()))
            .param("amount", "20")
        )
        .andExpect(view().name("redirect:/user/profile"));
    verify(bankAccountService).transferToUserAccount(user1.getIdUser(),account1.getIdAccount(),20);
  }
}
