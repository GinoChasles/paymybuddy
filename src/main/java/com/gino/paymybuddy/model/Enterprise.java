package com.gino.paymybuddy.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Enterprise {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id_enterprise", nullable = false)
  private int idEnterprise;

  @Column(name = "name")
  private String name;

  @Column(name = "siret")
  private String siret;

  @OneToOne(mappedBy = "enterprise")
//  @JoinColumn(name = "id_account", referencedColumnName = "id_account", nullable = false)
  private Account account;

  @OneToMany(mappedBy = "enterprise")
  private List<Commission> commission;

  public Enterprise(final String nameParam, final String siretParam) {
    name = nameParam;
    siret = siretParam;
  }

  public Enterprise() {
  }

  public int getIdEnterprise() {
    return idEnterprise;
  }

  public void setIdEnterprise(final int idEnterpriseParam) {
    idEnterprise = idEnterpriseParam;
  }

  public String getName() {
    return name;
  }

  public void setName(final String nameParam) {
    name = nameParam;
  }

  public String getSiret() {
    return siret;
  }

  public void setSiret(final String siretParam) {
    siret = siretParam;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(final Account accountParam) {
    account = accountParam;
  }

  public List<Commission> getCommission() {
    return commission;
  }

  public void setCommission(final List<Commission> commissionParam) {
    commission = commissionParam;
  }
}
