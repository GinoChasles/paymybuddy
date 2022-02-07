package com.gino.paymybuddy.service;

import com.gino.paymybuddy.model.Role;
import com.gino.paymybuddy.model.User;
import com.gino.paymybuddy.repository.RoleRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

  private final RoleRepository repository;
  private final UserService userService;

  public RoleServiceImpl(final RoleRepository repositoryParam,
                         final UserService userServiceParam) {
    repository = repositoryParam;
    userService = userServiceParam;
  }


  @Override
  public Optional<Role> findById(final int id) {
    return repository.findById(id);
  }

  @Override
  public Role insert(final Role roleParam) {
    return repository.save(roleParam);
  }

  @Override
  public void delete(final int id) {
    Optional<Role> optionalRoleLocal = this.findById(id);
    if (optionalRoleLocal.isPresent()) {
      Role roleLocal = optionalRoleLocal.get();
      repository.delete(roleLocal);
    }
  }

  @Override
  public Role update(final int id, final Role roleParam) {
    Optional<Role> optionalRoleLocal = this.findById(id);
    if (optionalRoleLocal.isPresent()) {
      Role roleLocal = optionalRoleLocal.get();
      roleLocal.setRole(roleParam.getRole());
      roleLocal.setDescription(roleParam.getDescription());
      repository.save(roleLocal);
    }
    return null;
  }


}
