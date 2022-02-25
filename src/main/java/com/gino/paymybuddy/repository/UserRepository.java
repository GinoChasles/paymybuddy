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

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findUserByEmail(final String email);


  @Query("SELECT u.friends FROM User u WHERE u.idUser = :id")
  List<User> findFriends(final int id);

  @Query("SELECT u.friends FROM User u WHERE u.idUser = :id")
  Page<User> findFriendsPage(final int id, Pageable pageableParam);

  @Query("SELECT u FROM User u WHERE u.username = :username")
  public User getUserByUsername(@Param("username") String username);

}
