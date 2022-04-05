package com.gino.paymybuddy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gino.paymybuddy.PaymybuddyApplication;
import com.gino.paymybuddy.service.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * The type Transaction controller it.
 */
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTestRun.sql")
@SpringBootTest(classes = PaymybuddyApplication.class)
@AutoConfigureMockMvc
public class TransactionControllerIT {

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
   * The Transaction service.
   */
  @Autowired
  TransactionServiceImpl transactionService;

  /**
   * Gets transactions test.
   *
   * @throws Exception the exception
   */
  @Test
  public void getTransactionsTest() throws Exception {
  mockMvc.perform(get("/transfer")
      .with(user("email@teste.com").password("testtest").roles("USER"))
      )
      .andExpect(status().isOk())
      .andExpect(view().name("transfer"))
      .andExpect(MockMvcResultMatchers.model()
          .attributeExists("transactions", "amountMax", "friendList", "postTransaction"));
    ;
  }

  /**
   * Save transaction test.
   *
   * @throws Exception the exception
   */
  @Test
  public void saveTransactionTest() throws Exception {
    mockMvc.perform(post("/transfer/saveTransaction")
        .with(user("email@teste.com").password("testtest").roles("USER"))
        .contentType( MediaType.parseMediaType("application/x-www-form-urlencoded"))
            .param("connection", "email2@teste.com")
            .param("amount", "10")
            .param("description", "testDBtest")
        )
        .andExpect(view().name("redirect:/transfer"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("postTransaction"));
  }

}
