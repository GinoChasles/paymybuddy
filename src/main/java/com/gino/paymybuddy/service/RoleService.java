package com.gino.paymybuddy.service;

import com.gino.paymybuddy.model.Role;
import java.util.Optional;

/**
 * The interface Role service.
 */
public interface RoleService {
  /**
   * Find by id optional.
   *
   * @param id the id
   * @return the optional
   */
  Optional<Role> findById(final int id);

  /**
   * Insert role.
   *
   * @param roleParam the role param
   * @return the role
   */
  Role insert(final Role roleParam);

  /**
   * Delete.
   *
   * @param id the id
   */
  void delete(final int id);

  /**
   * Update role.
   *
   * @param id        the id
   * @param roleParam the role param
   * @return the role
   */
  Role update(final int id, final Role roleParam);
}
