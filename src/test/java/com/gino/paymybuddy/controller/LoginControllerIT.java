package com.gino.paymybuddy.controller;

import com.gino.paymybuddy.PaymybuddyApplication;
import com.gino.paymybuddy.service.UserServiceImpl;
import com.gino.paymybuddy.utils.AppPropertiesExt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * The type Login controller it.
 */
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTestRun.sql")
@SpringBootTest(classes = PaymybuddyApplication.class)
@AutoConfigureMockMvc

public class LoginControllerIT {
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
   * The App properties ext.
   */
  @Autowired
  AppPropertiesExt appPropertiesExt;

  /**
   * Gets transactions test.
   *
   * @throws Exception the exception
   */
  @Test
  public void getTransactionsTest() throws Exception {
    mockMvc.perform(get("/home")
            .with(user("email@teste.com").password("testtest").roles("USER")))
        .andExpect(MockMvcResultMatchers.model().attribute("currentPrincipalName", "test"))
        .andReturn();
  }

  /**
   * Gets register test.
   *
   * @throws Exception the exception
   */
  @Test
  public void getRegisterTest() throws Exception {
    mockMvc.perform(get("/register")).andExpect(view().name("register"));
  }

  /**
   * Register test.
   *
   * @throws Exception the exception
   */
  @Test
  public void registerTest() throws Exception {
    mockMvc.perform(post("/register")
        .contentType( MediaType.parseMediaType("application/x-www-form-urlencoded"))
        .param("email", "emailTest@teste.com")
        .param("username", "TestUser")
        .param("password", "testDBUser"))
        .andExpect(status().isOk())
        .andExpect(view().name("login"));
  }

  /**
   * Gets admin test.
   *
   * @throws Exception the exception
   */
  @Test
  @WithMockUser(username = "user1", password = "pwd", roles = "ADMIN")
  public void getAdminTest() throws Exception {
    MvcResult result = mockMvc.perform(get("/admin")).andReturn();
    String content = result.getResponse().getContentAsString();
    assertThat(content).isEqualTo("Admin !");
  }
}
