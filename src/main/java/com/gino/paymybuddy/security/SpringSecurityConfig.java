package com.gino.paymybuddy.security;

import com.gino.paymybuddy.service.CustomUserDetailsService;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


  private final DataSource dataSourceParam;

  public SpringSecurityConfig(final DataSource dataSourceParamParam) {
    dataSourceParam = dataSourceParamParam;
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
        .httpBasic();
    http
        .authorizeRequests()
        .antMatchers("/user", "/transaction/saveTransaction", "/transfer").hasRole("USER")
        .antMatchers("/login", "/", "/user/register", "/home").permitAll()
        .antMatchers("/admin").hasRole("ADMIN")
        .anyRequest().authenticated()
        .and()
        .cors().and().csrf().disable()
        .formLogin()
          .loginPage("/login")
          .permitAll()
          .failureUrl("/login")
          .defaultSuccessUrl("/home", true)
          .and()
        .logout()
        .logoutUrl("/logout")
        .logoutSuccessUrl("/login")
        .clearAuthentication(true)
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID")
        .permitAll();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return new CustomUserDetailsService();
  }
  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());

    return authProvider;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider());
  }

//  @Autowired
//  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//    auth.jdbcAuthentication()
//        .dataSource(dataSourceParam)
//        .usersByUsernameQuery("select username,password, email "
//            + "from user "
//            + "where username = ?");
//        .authoritiesByUsernameQuery("select email,authority "
//            + "from authorities "
//            + "where email = ?");
//  }

//  @Override
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
////    auth.inMemoryAuthentication()
////        .withUser("springuser").password(passwordEncoder().encode("spring123")).roles("USER")
////        .and()
////        .withUser("springadmin").password(passwordEncoder().encode("admin123"))
////        .roles("ADMIN", "USER");
//    auth.jdbcAuthentication()
//        .passwordEncoder(passwordEncoder())
//        .dataSource(dataSourceParam)
//        .usersByUsernameQuery("select username as principal, password as credentials from user where username = ?");
////        .authoritiesByUsernameQuery("select  user.id_user, username, id_role from user_role inner join user on user.id_user = user_role.id_user where username=?");
////        .authoritiesByUsernameQuery("select user_username as principal, role.role as role from user_role where user_username = ?");
//  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
