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
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int idCommission;

  @Column(name = "pourcentage")
  @PositiveOrZero
  private double pourcentage;

  @OneToOne
  @JoinColumn(name = "idTransaction", referencedColumnName = "idTransaction")
  private Transaction transaction;

  @ManyToOne
  @JoinColumn(name = "idEnterprise")
  private Enterprise enterprise;

  @ManyToOne
  @JoinColumn(name = "idUser")
  private User user;

  public Commission() {
  }

  public Commission(final double pourcentageParam) {
    pourcentage = pourcentageParam;
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

  public User getUser() {
    return user;
  }

  public void setUser(final User userParam) {
    user = userParam;
  }
}
