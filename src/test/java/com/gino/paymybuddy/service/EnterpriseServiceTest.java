package com.gino.paymybuddy.service;

import com.gino.paymybuddy.model.Enterprise;
import com.gino.paymybuddy.repository.EnterpriseRepository;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EnterpriseServiceTest {

  @InjectMocks
  private EnterpriseServiceImpl enterpriseService;

  @Mock
  private EnterpriseRepository enterpriseRepository;

  private static Enterprise enterprise;

  @BeforeEach
  void setUp() {
    enterprise = new Enterprise("name", "siret");
  }

  @Test
  public void findByIdTest() {
    when(enterpriseRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(enterprise));

    Optional<Enterprise> enterpriseOptionalLocal = enterpriseService.findById(1);
    Enterprise enterpriseLocal = new Enterprise();
    if (enterpriseOptionalLocal.isPresent()) {
      enterpriseLocal = enterpriseOptionalLocal.get();
    }

    assertThat(enterpriseLocal.getName()).isEqualTo("name");
    Mockito.verify(enterpriseRepository, Mockito.times(1)).findById(Mockito.anyInt());
  }

  @Test
  public void insertTest() {
    when(enterpriseRepository.save(Mockito.any(Enterprise.class))).thenReturn(enterprise);
    Enterprise enterpriseLocal = enterpriseService.insert(enterprise);

    assertThat(enterpriseLocal.getIdEnterprise()).isNotNull();
    assertThat(enterpriseLocal.getName()).isEqualTo("name");
  }

  @Test
  public void deleteTest() {
    ArgumentCaptor<Enterprise> argumentCaptor = ArgumentCaptor.forClass(Enterprise.class);
    when(enterpriseRepository.findById(anyInt())).thenReturn(Optional.ofNullable(enterprise));

      enterpriseService.delete(enterprise.getIdEnterprise());
      Mockito.verify(enterpriseRepository, Mockito.times(1)).delete(argumentCaptor.capture());

  }

  @Test
  public void updateTest() {
    when(enterpriseRepository.findById(anyInt())).thenReturn(Optional.ofNullable(enterprise));
    Enterprise enterpriseLocal = enterprise;
      enterpriseLocal.setName("nouveauName");
      enterpriseService.update(enterprise.getIdEnterprise(), enterpriseLocal);
      assertThat(enterprise.getName()).isEqualTo("nouveauName");
  }

  @Test
  public void updateTest_whenEnterpriseIsNull() {
    when(enterpriseRepository.findById(anyInt())).thenReturn(Optional.empty());

    Enterprise enterpriseLocal = enterpriseService.update(enterprise.getIdEnterprise(), enterprise);
    assertThat(enterpriseLocal).isNull();
  }
}
