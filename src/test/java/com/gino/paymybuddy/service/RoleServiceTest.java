package com.gino.paymybuddy.service;

import com.gino.paymybuddy.model.Role;
import com.gino.paymybuddy.repository.RoleRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {
  @InjectMocks
  private RoleServiceImpl roleService;
  @Mock
  private RoleRepository roleRepository;

  private Role role;

  @BeforeEach
  void setUp() {
    role = new Role("ROLE_USER", "user role");
  }

  @Test
  public void findByIdTest() {
    when(roleRepository.findById(anyInt())).thenReturn(Optional.of(role));

    Optional<Role> optionalRoleLocal = roleService.findById(1);
    Role roleTest = new Role();
    if (optionalRoleLocal.isPresent()) {
      roleTest = optionalRoleLocal.get();
    }

    assertThat(roleTest.getRole()).isEqualTo(role.getRole());
    verify(roleRepository, times(1)).findById(anyInt());
  }

  @Test
  public void insertTest() {
    when(roleRepository.save(any(Role.class))).thenReturn(role);
    Role roleTest = roleService.insert(role);

    assertThat(roleTest.getRole()).isEqualTo(role.getRole());
    assertThat(roleTest.getDescription()).isEqualTo(role.getDescription());
  }

  @Test
  public void deleteTest() {
    ArgumentCaptor<Role> argumentCaptor = ArgumentCaptor.forClass(Role.class);
    when(roleRepository.findById(anyInt())).thenReturn(Optional.of(role));

    roleService.delete(role.getRoleId());
    verify(roleRepository, times(1)).delete(argumentCaptor.capture());
  }

  @Test
  public void updateTest() {
    when(roleRepository.findById(anyInt())).thenReturn(Optional.of(role));
    Role roleTest = role;
    roleTest.setDescription("Changing user role description");

    roleService.update(role.getRoleId(), roleTest);

    assertThat(roleTest.getDescription()).isEqualTo(role.getDescription());
  }

  @Test
  public void updateTest_whenRoleIsNull() {
    when(roleRepository.findById(anyInt())).thenReturn(Optional.empty());

    Role roleTest = roleService.update(role.getRoleId(), role);
    assertThat(roleTest).isNull();
  }

}
