package com.gino.paymybuddy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_role")
  private int idRole;

  private String role;

  private String description;

  public Role() {
  }

  public Role(final String roleParam, final String descriptionParam) {
    role = roleParam;
    description = descriptionParam;
  }

  public int getRoleId() {
    return idRole;
  }

  public void setRoleId(final int roleIdParam) {
    idRole = roleIdParam;
  }

  public String getRole() {
    return role;
  }

  public void setRole(final String roleParam) {
    role = roleParam;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(final String descriptionParam) {
    description = descriptionParam;
  }
}
