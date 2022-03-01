package com.gino.paymybuddy.controller;

import com.gino.paymybuddy.PaymybuddyApplication;
import com.gino.paymybuddy.service.BankAccountService;
import com.gino.paymybuddy.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = PaymybuddyApplication.class)
@AutoConfigureMockMvc
public class BankAccountControllerTest {
  @Autowired
  MockMvc mockMvc;

  @Mock
  BankAccountService bankAccountService;

  @Mock
  UserService userService;

  @Test
  @WithMockUser(username = "user1", password = "pwd", roles = "USER")
  public void deleteBankAccountTest() throws Exception {
    mockMvc.perform(post("/bankAccount/1")).andExpect(redirectedUrl("/user/profile"));
  }
}
