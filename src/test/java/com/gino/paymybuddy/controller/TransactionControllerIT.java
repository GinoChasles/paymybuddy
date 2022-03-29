package com.gino.paymybuddy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gino.paymybuddy.PaymybuddyApplication;
import com.gino.paymybuddy.dto.TransactionDTO;
import com.gino.paymybuddy.service.TransactionServiceImpl;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = PaymybuddyApplication.class)
@AutoConfigureMockMvc
public class TransactionControllerIT {

  @Inject
  MockMvc mockMvc;

  @Autowired
  ObjectMapper mapper;

  @Mock
  TransactionServiceImpl transactionService;

  @Test
  public void getTransactionsTest() throws Exception {
  mockMvc.perform(get("/transfer")
      .with(user("email@teste.com").password("testtest").roles("USER"))
      )
      .andExpect(status().isOk())
      .andExpect(view().name("transfer"));
  }

  @Test

  public void saveTransactionTest() throws Exception {
    String json = mapper.writeValueAsString(new TransactionDTO());
    mockMvc.perform(post("/transfer/saveTransaction")
        .with(user("email@teste.com").password("testtest").roles("USER"))
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(view().name("redirect:/transfer"));
  }
}
