package com.gino.paymybuddy.service;

import com.gino.paymybuddy.model.Enterprise;
import java.util.Optional;

public interface EnterpriseService {
  Optional<Enterprise> findById(final int id);
  Enterprise insert(final Enterprise enterpriseParam);
  Enterprise update(final int id, final Enterprise enterpriseParam);
  void delete(final int id);
}
