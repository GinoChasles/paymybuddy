package com.gino.paymybuddy.repository;

import com.gino.paymybuddy.model.Commission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Commission repository.
 */
@Repository
public interface CommissionRepository extends JpaRepository<Commission, Integer> {

  /**
   * Count all by enterprise id enterprise double.
   *
   * @param idEnterprise the id enterprise
   * @return the double
   */
  double countAllByEnterprise_IdEnterprise(final int idEnterprise);
}
