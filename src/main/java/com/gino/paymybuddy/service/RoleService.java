package com.gino.paymybuddy.service;

import com.gino.paymybuddy.model.Role;
import java.util.Optional;

public interface RoleService {
  Optional<Role> findById(final int id);
  Role insert(final Role roleParam);
  void delete(final int id);
  Role update(final int id, final Role roleParam);
}
