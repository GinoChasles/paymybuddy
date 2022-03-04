package com.gino.paymybuddy.service;

import com.gino.paymybuddy.model.Account;
import com.gino.paymybuddy.model.Commission;
import com.gino.paymybuddy.model.User;
import com.gino.paymybuddy.repository.CommissionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommissionServiceTest {

  @InjectMocks
  private CommissionServiceImpl commissionService;

  @Mock
  private CommissionRepository commissionRepository;

  private static Commission commission1;
  private static Commission commission2;
  private static List<Commission> commissionList;

  @BeforeEach
  void setUp() {
    commission1 = new Commission(0.5, 0.5);
    commission2 = new Commission(0.5, 100);
    commissionList = new ArrayList<>();
    commissionList.add(commission1);
    commissionList.add(commission2);
  }

  @Test
  public void findByIdTest() {
    when(commissionRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(commission1));

    Optional<Commission> optionalLocal = commissionService.findById(1);
    Commission commissionLocal = new Commission();
    if (optionalLocal.isPresent()) {
      commissionLocal = optionalLocal.get();
    }

    assertThat(commissionLocal.getCommisssionCount()).isEqualTo(0.5);
    Mockito.verify(commissionRepository, Mockito.times(1)).findById(Mockito.anyInt());
  }

  @Test
  public void insertTest() {
    when(commissionRepository.save(Mockito.any(Commission.class))).thenReturn(commission1);
    Commission commissionLocal = commissionService.insert(commission1);

    assertThat(commissionLocal.getIdCommission()).isNotNull();
    assertThat(commissionLocal.getCommisssionCount()).isEqualTo(0.5);
    assertThat(commissionLocal.getPourcentage()).isEqualTo(0.5);
  }

  @Test
  public void deleteTest() {
    ArgumentCaptor<Commission> argumentCaptor = ArgumentCaptor.forClass(Commission.class);
    Optional<Commission> optionalCommissionLocal = commissionRepository.findById(commission1.getIdCommission());
    Commission commissionLocal;
    if(optionalCommissionLocal.isPresent()) {
      commissionLocal = optionalCommissionLocal.get();
      commissionService.delete(commissionLocal.getIdCommission());
      Mockito.verify(commissionRepository, Mockito.times(1)).delete(argumentCaptor.capture());
    }
  }

  @Test
  public void updateTest() {
    Optional<Commission> optionalCommissionLocal = commissionRepository.findById(commission1.getIdCommission());
    Commission commissionLocal;
    if(optionalCommissionLocal.isPresent()) {
      commissionLocal = optionalCommissionLocal.get();
      commissionLocal.setPourcentage(1);
      when(commissionService.update(commission1.getIdCommission(), commissionLocal)).thenReturn(commission1);
      assertThat(commission1.getPourcentage()).isEqualTo(1);
    }
  }

  @Test
  public void getTotalCommissionForEnterpriseTest() {
    double expected = commission1.getCommisssionCount() + commission2.getCommisssionCount();
    when(commissionRepository.countAllByEnterprise_IdEnterprise(Mockito.anyInt())).thenReturn(expected);
    double result = commissionService.getTotalCommissionForEnterprise(Mockito.anyInt());
    assertThat(result).isEqualTo(expected);
  }
}
