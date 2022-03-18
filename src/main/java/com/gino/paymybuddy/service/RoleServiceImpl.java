package com.gino.paymybuddy.service;

import com.gino.paymybuddy.model.Role;
import com.gino.paymybuddy.model.User;
import com.gino.paymybuddy.repository.RoleRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

  private final RoleRepository repository;

  public RoleServiceImpl(final RoleRepository repositoryParam) {
    repository = repositoryParam;
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
    optionalRoleLocal.ifPresent(repository::delete);
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
