package com.gino.paymybuddy.service;

import com.gino.paymybuddy.model.Enterprise;
import java.util.Optional;

/**
 * The interface Enterprise service.
 */
public interface EnterpriseService {
  /**
   * Find by id optional.
   *
   * @param id the id
   * @return the optional
   */
  Optional<Enterprise> findById(final int id);

  /**
   * Insert enterprise.
   *
   * @param enterpriseParam the enterprise param
   * @return the enterprise
   */
  Enterprise insert(final Enterprise enterpriseParam);

  /**
   * Update enterprise.
   *
   * @param id              the id
   * @param enterpriseParam the enterprise param
   * @return the enterprise
   */
  Enterprise update(final int id, final Enterprise enterpriseParam);

  /**
   * Delete.
   *
   * @param id the id
   */
  void delete(final int id);
}
