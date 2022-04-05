package com.gino.paymybuddy.repository;

import com.gino.paymybuddy.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * The interface User repository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  /**
   * Find user by email optional.
   *
   * @param email the email
   * @return the optional
   */
  Optional<User> findUserByEmail(final String email);


  /**
   * Find friends list.
   *
   * @param id the id
   * @return the list
   */
  @Query("SELECT u.friends FROM User u WHERE u.idUser = :id")
  List<User> findFriends(final int id);

  /**
   * Find friends page page.
   *
   * @param id            the id
   * @param pageableParam the pageable param
   * @return the page
   */
  @Query("SELECT u.friends FROM User u WHERE u.idUser = :id")
  Page<User> findFriendsPage(final int id, Pageable pageableParam);

  /**
   * Gets user by username.
   *
   * @param username the username
   * @return the user by username
   */
  @Query("SELECT u FROM User u WHERE u.username = :username")
  public User getUserByUsername(@Param("username") String username);

}
