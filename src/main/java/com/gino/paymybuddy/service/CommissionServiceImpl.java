package com.gino.paymybuddy.service;

import com.gino.paymybuddy.model.Commission;
import com.gino.paymybuddy.repository.CommissionRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CommissionServiceImpl implements CommissionService{

  private final CommissionRepository commissionRepository;

  public CommissionServiceImpl(
      final CommissionRepository commissionRepositoryParam) {
    commissionRepository = commissionRepositoryParam;
  }


  @Override
  public Optional<Commission> findById(final int id) {
    return commissionRepository.findById(id);
  }

  @Override
  public Commission insert(final Commission commissionParam) {
    return commissionRepository.save(commissionParam);
  }

  @Override
  public void delete(final int id) {
      commissionRepository.deleteById(id);
  }

  @Override
  public Commission update(final int id, final Commission commissionParam) {
    Optional<Commission> commissionOptionalLocal = this.findById(id);
    if (commissionOptionalLocal.isPresent()) {
      Commission commissionLocal = commissionOptionalLocal.get();
      commissionLocal.setPourcentage(commissionParam.getPourcentage());
      return commissionRepository.save(commissionLocal);
    } else {
      return null;
    }
  }
}
