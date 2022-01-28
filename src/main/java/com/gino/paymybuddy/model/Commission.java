package com.gino.paymybuddy.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class Commission {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id_commission", nullable = false)
  private int idCommission;

  @Column(name = "pourcentage")
  @PositiveOrZero
  private double pourcentage;

  @Column(name = "count")
  @PositiveOrZero
  private double commisssionCount;

  @OneToOne
  @JoinColumn(name = "id_transaction", referencedColumnName = "id_transaction")
  private Transaction transaction;

  @ManyToOne
  @JoinColumn(name = "id_enterprise")
  private Enterprise enterprise;

//  @ManyToOne
//  @JoinColumn(name = "idUser")
//  private User user;

  public Commission() {
  }

  public double getCommisssionCount() {
    return commisssionCount;
  }

  public void setCommisssionCount(final double countParam) {
    commisssionCount = countParam;
  }

  public Commission(final double pourcentageParam, final double countParam) {
    pourcentage = pourcentageParam;
    commisssionCount = countParam;
  }

  public int getIdCommission() {
    return idCommission;
  }

  public void setIdCommission(final int idCommissionParam) {
    idCommission = idCommissionParam;
  }

  public double getPourcentage() {
    return pourcentage;
  }

  public void setPourcentage(final double pourcentageParam) {
    pourcentage = pourcentageParam;
  }

  public Transaction getTransaction() {
    return transaction;
  }

  public void setTransaction(final Transaction transactionParam) {
    transaction = transactionParam;
  }

  public Enterprise getEnterprise() {
    return enterprise;
  }

  public void setEnterprise(final Enterprise enterpriseParam) {
    enterprise = enterpriseParam;
  }

//  public User getUser() {
//    return user;
//  }
//
//  public void setUser(final User userParam) {
//    user = userParam;
//  }
}
