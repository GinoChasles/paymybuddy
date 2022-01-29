package com.gino.paymybuddy.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
        .httpBasic()
        .and()
        .cors().and().csrf().disable()
        .authorizeRequests()
        .antMatchers("/", "/home").permitAll()
        .antMatchers("/admin").hasRole("ADMIN")
        .antMatchers("/user", "/transaction/saveTransaction", "/transaction").hasRole("USER")
        .anyRequest().authenticated()
        .and()
        .formLogin()
          .loginPage("/login")
          .permitAll()
          .and()
        .logout()
          .permitAll();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser("springuser").password(passwordEncoder().encode("spring123")).roles("USER")
        .and()
        .withUser("springadmin").password(passwordEncoder().encode("admin123"))
        .roles("ADMIN", "USER");
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
