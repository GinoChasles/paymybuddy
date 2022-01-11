package com.gino.paymybuddy.repository;

import com.gino.paymybuddy.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findUserByEmail(final String email);

  List<User> findAllFriendsByUserId(final int id);
}
