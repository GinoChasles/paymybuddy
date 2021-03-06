package com.gino.paymybuddy.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The type Mvc config.
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

  public void addViewControllers(final ViewControllerRegistry registry) {
    registry.addViewController("/login").setViewName("login");
    registry.addViewController("/").setViewName("default");
    registry.addViewController("/home").setViewName("home");
  }
}
