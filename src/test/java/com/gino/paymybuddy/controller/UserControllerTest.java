package com.gino.paymybuddy.controller;

import com.gino.paymybuddy.PaymybuddyApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = PaymybuddyApplication.class)
@AutoConfigureMockMvc
public class UserControllerTest {

  @Autowired
  MockMvc mockMvc;
}
