package com.gino.paymybuddy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gino.paymybuddy.PaymybuddyApplication;
import com.gino.paymybuddy.model.Account;
import com.gino.paymybuddy.service.BankAccountServiceImpl;
import com.gino.paymybuddy.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * The type Bank account controller it.
 */
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTestRun.sql")
@SpringBootTest(classes = PaymybuddyApplication.class)
@AutoConfigureMockMvc
public class BankAccountControllerIT {

  /**
   * The Mock mvc.
   */
  @Autowired
  MockMvc mockMvc;

  /**
   * The Mapper.
   */
  @Autowired
  ObjectMapper mapper;

  /**
   * The Bank account service.
   */
  @Autowired
  BankAccountServiceImpl bankAccountService;

  /**
   * The User service.
   */
  @Autowired
  UserServiceImpl userService;

  private Account account1;

  private static final MediaType FORM_URL =
      MediaType.parseMediaType("application/x-www-form-urlencoded");

  /**
   * Sets up.
   */
  @BeforeEach
  public void setUp() {
    account1 = new Account(12345, "test1", "testAccountNumber1", 500);

  }

  /**
   * Delete bank account test when bank account exist and user log.
   *
   * @throws Exception the exception
   */
  @Test
  @WithMockUser(username = "user1", password = "pwd", roles = "USER")
  public void deleteBankAccountTest_whenBankAccountExistAndUserLog() throws Exception {
    mockMvc.perform(delete("/bankAccount/2")).andExpect(redirectedUrl("/user/profile"));
  }

  /**
   * Delete bank account test when bank account exist and user not log.
   *
   * @throws Exception the exception
   */
  @Test
  public void deleteBankAccountTest_whenBankAccountExistAndUserNotLog() throws Exception {
    mockMvc.perform(delete("/bankAccount/2")).andExpect(status().is4xxClientError());
  }

  /**
   * Add bank account test when user log exception.
   */
  @Test
  public void addBankAccountTest_whenUserLog_Exception() {

    org.assertj.core.api.Assertions.assertThatThrownBy(
            () -> mockMvc.perform(MockMvcRequestBuilders.post("/bankAccount/")
                    .with(user("email@teste.com").password("testtest").roles("USER")))
                .andExpect(status().isOk()))
        .hasCause(new DataIntegrityViolationException(
            "could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement"));

  }

  /**
   * Add bank account test when user log.
   *
   * @throws Exception the exception
   */
  @Test
  public void addBankAccountTest_whenUserLog() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/bankAccount")
            .with(user("email@teste.com").password("testtest").roles("USER"))

            .contentType(FORM_URL)
            .param("iban", String.valueOf(account1.getIban()))
            .param("accountnumber", String.valueOf(account1.getAccountnumber()))
            .param("bic", String.valueOf(account1.getBic()))
            .param("amount", String.valueOf(account1.getAmount()))
        )
        .andExpect(view().name("redirect:/user/profile"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("addBank"));

  }

  /**
   * Transfer to bank account test.
   *
   * @throws Exception the exception
   */
  @Test
  public void transferToBankAccountTest() throws Exception {
    mockMvc.perform(post("/bankAccount/transferIn")
            .with(user("email@teste.com").password("testtest").roles("USER"))
            .contentType(FORM_URL)

            .param("idAccount", "3")
            .param("amount", "20")
        )
        .andExpect(view().name("redirect:/user/profile"));
  }

  /**
   * Transfer to amount test.
   *
   * @throws Exception the exception
   */
  @Test
  public void transferToAmountTest() throws Exception {
    mockMvc.perform(post("/bankAccount/transferOut")
            .with(user("email@teste.com").password("testtest").roles("USER"))
            .contentType(FORM_URL)

            .param("idAccount", "3")
            .param("amount", "20")
        )
        .andExpect(view().name("redirect:/user/profile"));
  }
}
