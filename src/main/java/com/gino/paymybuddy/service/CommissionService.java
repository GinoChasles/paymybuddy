package com.gino.paymybuddy.service;

import com.gino.paymybuddy.model.Commission;
import java.util.Optional;

public interface CommissionService {
  Optional<Commission> findById(final int id);
  Commission insert(final Commission commissionParam);
  void delete(final int id);
  Commission update(final int id, final Commission commissionParam);
}
