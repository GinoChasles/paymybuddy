package com.gino.paymybuddy.repository;

import com.gino.paymybuddy.model.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Enterprise repository.
 */
@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Integer> {


}
