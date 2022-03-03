package com.gino.paymybuddy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gino.paymybuddy.PaymybuddyApplication;
import com.gino.paymybuddy.model.Account;
import com.gino.paymybuddy.service.BankAccountService;
import com.gino.paymybuddy.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = PaymybuddyApplication.class)
@AutoConfigureMockMvc
public class BankAccountControllerTest {
  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper mapper;

  @Mock
  BankAccountService bankAccountService;

  @Mock
  UserService userService;


  @Test
  @WithMockUser(username = "user1", password = "pwd", roles = "USER")
  public void deleteBankAccountTest_whenBankAccountExistAndUserLog() throws Exception {
    mockMvc.perform(post("/bankAccount/1")).andExpect(redirectedUrl("/user/profile"));
  }

  @Test
  public void deleteBankAccountTest_whenBankAccountExistAndUserNotLog() throws Exception {
    mockMvc.perform(post("/bankAccount/1")).andExpect(status().is4xxClientError());
  }

//  @Test
//  @WithMockUser(username = "user1", password = "pwd", roles = "USER")
//  public void addBankAccountTest_whenUserLog() throws Exception {
//    Account accountParam = new Account( 12345,"test", "testtest", 14.54 );
//    String json = mapper.writeValueAsString(accountParam);
//
//    mockMvc.perform(MockMvcRequestBuilders.post("/bankAccount")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(json)
//            .accept(MediaType.APPLICATION_JSON))
//        .andDo(print())
//                .andExpect(status().is3xxRedirection())
//        .andExpect(view().name("redirect:/user/profile"));
//        .andExpect(status().isCreated());


//    mockMvc.perform(post("/bankAccount")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(json)
//        .param("ibanParam", "12345")
//        .param("accountnumberParam","testtest")
//        .param("amountParam","14.54")
//            .sessionAttr("addBank", new Account()))
//        .flashAttr("addBank", accountParam ))
//        .andExpect(status().is3xxRedirection())
//        .andExpect(view().name("redirect:/user/profile"));
//  }

}
