package com.gino.paymybuddy.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * The type Enterprise.
 */
@Entity
public class Enterprise {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_enterprise", nullable = false)
  private int idEnterprise;

  @Column(name = "name")
  private String name;

  @Column(name = "siret")
  private String siret;

  @OneToOne(mappedBy = "enterprise")
  private Account account;

  @OneToMany(mappedBy = "enterprise")
  private List<Commission> commission;

  /**
   * Instantiates a new Enterprise.
   *
   * @param nameParam  the name param
   * @param siretParam the siret param
   */
  public Enterprise(final String nameParam, final String siretParam) {
    name = nameParam;
    siret = siretParam;
  }

  /**
   * Instantiates a new Enterprise.
   */
  public Enterprise() {
  }

  /**
   * Gets id enterprise.
   *
   * @return the id enterprise
   */
  public int getIdEnterprise() {
    return idEnterprise;
  }

  /**
   * Sets id enterprise.
   *
   * @param idEnterpriseParam the id enterprise param
   */
  public void setIdEnterprise(final int idEnterpriseParam) {
    idEnterprise = idEnterpriseParam;
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name.
   *
   * @param nameParam the name param
   */
  public void setName(final String nameParam) {
    name = nameParam;
  }

  /**
   * Gets siret.
   *
   * @return the siret
   */
  public String getSiret() {
    return siret;
  }

  /**
   * Sets siret.
   *
   * @param siretParam the siret param
   */
  public void setSiret(final String siretParam) {
    siret = siretParam;
  }

  /**
   * Gets account.
   *
   * @return the account
   */
  public Account getAccount() {
    return account;
  }

  /**
   * Sets account.
   *
   * @param accountParam the account param
   */
  public void setAccount(final Account accountParam) {
    account = accountParam;
  }

  /**
   * Gets commission.
   *
   * @return the commission
   */
  public List<Commission> getCommission() {
    return commission;
  }

  /**
   * Sets commission.
   *
   * @param commissionParam the commission param
   */
  public void setCommission(final List<Commission> commissionParam) {
    commission = commissionParam;
  }
}
