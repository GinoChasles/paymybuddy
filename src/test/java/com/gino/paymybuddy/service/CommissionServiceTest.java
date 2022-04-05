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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * The type Commission service test.
 */
@ExtendWith(MockitoExtension.class)
public class CommissionServiceTest {

  @InjectMocks
  private CommissionServiceImpl commissionService;

  @Mock
  private CommissionRepository commissionRepository;

  private Commission commission1;
  private Commission commission2;
  private List<Commission> commissionList;

  /**
   * Sets up.
   */
  @BeforeEach
  void setUp() {
    commission1 = new Commission(0.5, 0.5);
    commission2 = new Commission(0.5, 100);
    commissionList = new ArrayList<>();
    commissionList.add(commission1);
    commissionList.add(commission2);
  }

  /**
   * Find by id test.
   */
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

  /**
   * Insert test.
   */
  @Test
  public void insertTest() {
    when(commissionRepository.save(Mockito.any(Commission.class))).thenReturn(commission1);
    Commission commissionLocal = commissionService.insert(commission1);

    assertThat(commissionLocal.getIdCommission()).isNotNull();
    assertThat(commissionLocal.getCommisssionCount()).isEqualTo(0.5);
    assertThat(commissionLocal.getPourcentage()).isEqualTo(0.5);
  }

  /**
   * Delete test.
   */
  @Test
  public void deleteTest() {
    ArgumentCaptor<Commission> argumentCaptor = ArgumentCaptor.forClass(Commission.class);
    when(commissionRepository.findById(anyInt())).thenReturn(
        Optional.of(commission1));

      commissionService.delete(commission1.getIdCommission());
      Mockito.verify(commissionRepository, Mockito.times(1)).delete(argumentCaptor.capture());

  }

  /**
   * Update test not present.
   */
  @Test
  public void updateTest_NotPresent() {
    when(commissionRepository.findById(anyInt())).thenReturn(Optional.empty());
    Commission commissionLocal = commissionService.update(commission1.getIdCommission(), commission1);
      assertThat(commissionLocal).isNull();
  }

  /**
   * Update test.
   */
  @Test
  public void updateTest() {
    when(commissionRepository.findById(commission1.getIdCommission())).thenReturn(Optional.of(commission1));
    Commission commissionLocal = commission1;

      commissionLocal.setPourcentage(1);
      commissionService.update(commission1.getIdCommission(), commissionLocal);
      assertThat(commission1.getPourcentage()).isEqualTo(1);

  }

  /**
   * Gets total commission for enterprise test.
   */
  @Test
  public void getTotalCommissionForEnterpriseTest() {
    double expected = commission1.getCommisssionCount() + commission2.getCommisssionCount();
    when(commissionRepository.countAllByEnterprise_IdEnterprise(Mockito.anyInt())).thenReturn(expected);
    double result = commissionService.getTotalCommissionForEnterprise(Mockito.anyInt());
    assertThat(result).isEqualTo(expected);
  }
}
