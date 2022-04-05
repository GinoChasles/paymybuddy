package com.gino.paymybuddy.service;

import com.gino.paymybuddy.model.Commission;
import java.util.Optional;

/**
 * The interface Commission service.
 */
public interface CommissionService {
  /**
   * Find by id optional.
   *
   * @param id the id
   * @return the optional
   */
  Optional<Commission> findById(final int id);

  /**
   * Insert commission.
   *
   * @param commissionParam the commission param
   * @return the commission
   */
  Commission insert(final Commission commissionParam);

  /**
   * Delete.
   *
   * @param id the id
   */
  void delete(final int id);

  /**
   * Update commission.
   *
   * @param id              the id
   * @param commissionParam the commission param
   * @return the commission
   */
  Commission update(final int id, final Commission commissionParam);

  /**
   * Gets total commission for enterprise.
   *
   * @param idEnterprise the id enterprise
   * @return the total commission for enterprise
   */
  double getTotalCommissionForEnterprise(final int idEnterprise);
}
