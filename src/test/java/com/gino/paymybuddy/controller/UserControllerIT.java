package com.gino.paymybuddy.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.gino.paymybuddy.PaymybuddyApplication;
import com.gino.paymybuddy.service.BankAccountService;
import com.gino.paymybuddy.service.BankAccountServiceImpl;
import com.gino.paymybuddy.service.TransactionService;
import com.gino.paymybuddy.service.TransactionServiceImpl;
import com.gino.paymybuddy.service.UserService;
import com.gino.paymybuddy.service.UserServiceImpl;
import com.gino.paymybuddy.utils.AppPropertiesExt;
import com.gino.paymybuddy.utils.LoadingUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * The type User controller it.
 */
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTestRun.sql")

@SpringBootTest(classes = PaymybuddyApplication.class)
@AutoConfigureMockMvc
public class UserControllerIT {

  /**
   * The Mock mvc.
   */
  @Autowired
  MockMvc mockMvc;

  /**
   * The User service.
   */
  @Autowired
  UserServiceImpl userService;
  /**
   * The Transaction service.
   */
  @Autowired
  TransactionServiceImpl transactionService;
  /**
   * The Bank account service.
   */
  @Autowired
  BankAccountServiceImpl bankAccountService;
  /**
   * The App properties ext.
   */
  @Autowired
  AppPropertiesExt appPropertiesExt;
  /**
   * The Loading user.
   */
  @Autowired
  LoadingUser loadingUser;

  /**
   * Save friend test.
   *
   * @throws Exception the exception
   */
  @Test
  public void saveFriendTest() throws Exception {
    mockMvc.perform(post("/user/contact")
        .with(user("email@teste.com").password("testtest").roles("USER"))
        .contentType( MediaType.parseMediaType("application/x-www-form-urlencoded"))
        .param("email", "email2@teste.com"))
        .andExpect(view().name("/contact"));
  }

  /**
   * Save friend test user doest not exist.
   *
   * @throws Exception the exception
   */
  @Test
  public void saveFriendTest_UserDoestNotExist() throws Exception {
     mockMvc.perform(post("/user/contact")
            .with(user("email@teste.com").password("testtest").roles("USER"))
            .contentType( MediaType.parseMediaType("application/x-www-form-urlencoded"))
            .param("email", "notExist@teste.com"))
        .andExpect(view().name("/contact"))
         .andExpect(MockMvcResultMatchers.model().attributeExists("userDoesNotExist"));

  }

  /**
   * Profile test.
   *
   * @throws Exception the exception
   */
  @Test
  public void profileTest() throws Exception {
    mockMvc.perform(get("/user/profile")
        .with(user("email@teste.com").password("testtest").roles("USER"))
        ).andExpect(view().name("/profile"))
        .andExpect(MockMvcResultMatchers.model()
            .attributeExists("accountBalance", "transactions", "listBankAccount", "addBank"));

  }


  /**
   * Gets contact test.
   *
   * @throws Exception the exception
   */
  @Test
  public void getContactTest() throws Exception {
    mockMvc.perform(get("/user/contact")
        .with(user("email@teste.com").password("testtest").roles("USER")))
        .andExpect(view().name("/contact"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("friendsList"));
  }


}
