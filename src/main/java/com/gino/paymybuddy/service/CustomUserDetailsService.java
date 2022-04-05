package com.gino.paymybuddy.service;

import com.gino.paymybuddy.model.Role;
import com.gino.paymybuddy.model.User;
import com.gino.paymybuddy.repository.UserRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * The type Custom user details service.
 */
public class CustomUserDetailsService implements UserDetailsService {

  /**
   * The User repository.
   */
  @Autowired
  UserRepository userRepository;



  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

//      User user = userRepository.getUserByUsername(username);
      Optional<User> userOptionalLocal = userRepository.findUserByEmail(username);
    if (userOptionalLocal.isEmpty()) {
      throw new UsernameNotFoundException("Could not find user");
    } else {
      User user = userOptionalLocal.get();
    List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());
    return buildUserForAuthentication(user, authorities);
    }
  }

  private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user,
                                                                                        List<GrantedAuthority> authorities) {
    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),true, true, true, true, authorities);
  }

  private List<GrantedAuthority> buildUserAuthority(List<Role> userRoles) {

    Set<GrantedAuthority> setAuths = new HashSet<>();
    // Build user's authorities
    for (Role userRole : userRoles) {
      setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
    }
    List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

    return Result;
  }
}
