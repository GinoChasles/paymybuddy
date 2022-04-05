package com.gino.paymybuddy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The type Role.
 */
@Entity
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_role")
  private int idRole;

  private String role;

  private String description;

  /**
   * Instantiates a new Role.
   */
  public Role() {
  }

  /**
   * Instantiates a new Role.
   *
   * @param roleParam        the role param
   * @param descriptionParam the description param
   */
  public Role(final String roleParam, final String descriptionParam) {
    role = roleParam;
    description = descriptionParam;
  }

  /**
   * Gets role id.
   *
   * @return the role id
   */
  public int getRoleId() {
    return idRole;
  }

  /**
   * Sets role id.
   *
   * @param roleIdParam the role id param
   */
  public void setRoleId(final int roleIdParam) {
    idRole = roleIdParam;
  }

  /**
   * Gets role.
   *
   * @return the role
   */
  public String getRole() {
    return role;
  }

  /**
   * Sets role.
   *
   * @param roleParam the role param
   */
  public void setRole(final String roleParam) {
    role = roleParam;
  }

  /**
   * Gets description.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets description.
   *
   * @param descriptionParam the description param
   */
  public void setDescription(final String descriptionParam) {
    description = descriptionParam;
  }
}
