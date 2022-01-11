package com.gino.paymybuddy.service;

import com.gino.paymybuddy.model.Enterprise;
import com.gino.paymybuddy.repository.EnterpriseRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseServiceImpl implements EnterpriseService{

  private final EnterpriseRepository enterpriseRepository;

  public EnterpriseServiceImpl(
      final EnterpriseRepository enterpriseRepositoryParam) {
    enterpriseRepository = enterpriseRepositoryParam;
  }


  @Override
  public Optional<Enterprise> findById(final int id) {
    return enterpriseRepository.findById(id);
  }

  @Override
  public Enterprise insert(final Enterprise enterpriseParam) {
    return enterpriseRepository.save(enterpriseParam);
  }

  @Override
  public Enterprise update(final int id, final Enterprise enterpriseParam) {
    Optional<Enterprise> enterpriseOptionalLocal = this.findById(id);
    if (enterpriseOptionalLocal.isPresent()) {
      Enterprise enterpriseLocal = enterpriseOptionalLocal.get();
      enterpriseLocal.setName(enterpriseParam.getName());
      enterpriseLocal.setSiret(enterpriseParam.getSiret());
      return enterpriseRepository.save(enterpriseLocal);
    } else {
      return null;
    }
  }

  @Override
  public void delete(final int id) {
    enterpriseRepository.deleteById(id);
  }
}
