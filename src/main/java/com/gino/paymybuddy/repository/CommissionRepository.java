package com.gino.paymybuddy.repository;

import com.gino.paymybuddy.model.Commission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommissionRepository extends JpaRepository<Commission, Integer> {
}
