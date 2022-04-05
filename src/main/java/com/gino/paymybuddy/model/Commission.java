package com.gino.paymybuddy.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.PositiveOrZero;

/**
 * The type Commission.
 */
@Entity
public class Commission {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_commission", nullable = false)
  private int idCommission;

  @Column(name = "pourcentage")
  @PositiveOrZero
  private double pourcentage;

  @Column(name = "count", columnDefinition = "Decimal(10,2)")
  @PositiveOrZero
  private double commisssionCount;

  @OneToOne
  @JsonManagedReference
  @JoinColumn(name = "id_transaction", referencedColumnName = "id_transaction")
  private Transaction transaction;

  @ManyToOne
  @JoinColumn(name = "id_enterprise")
  private Enterprise enterprise;


  /**
   * Instantiates a new Commission.
   */
  public Commission() {
  }

  /**
   * Gets commisssion count.
   *
   * @return the commisssion count
   */
  public double getCommisssionCount() {
    return commisssionCount;
  }

  /**
   * Sets commisssion count.
   *
   * @param countParam the count param
   */
  public void setCommisssionCount(final double countParam) {
    commisssionCount = countParam;
  }

  /**
   * Instantiates a new Commission.
   *
   * @param pourcentageParam the pourcentage param
   * @param countParam       the count param
   */
  public Commission(final double pourcentageParam, final double countParam) {
    pourcentage = pourcentageParam;
    commisssionCount = countParam;
  }

  /**
   * Gets id commission.
   *
   * @return the id commission
   */
  public int getIdCommission() {
    return idCommission;
  }

  /**
   * Sets id commission.
   *
   * @param idCommissionParam the id commission param
   */
  public void setIdCommission(final int idCommissionParam) {
    idCommission = idCommissionParam;
  }

  /**
   * Gets pourcentage.
   *
   * @return the pourcentage
   */
  public double getPourcentage() {
    return pourcentage;
  }

  /**
   * Sets pourcentage.
   *
   * @param pourcentageParam the pourcentage param
   */
  public void setPourcentage(final double pourcentageParam) {
    pourcentage = pourcentageParam;
  }

  /**
   * Gets transaction.
   *
   * @return the transaction
   */
  public Transaction getTransaction() {
    return transaction;
  }

  /**
   * Sets transaction.
   *
   * @param transactionParam the transaction param
   */
  public void setTransaction(final Transaction transactionParam) {
    transaction = transactionParam;
  }

  /**
   * Gets enterprise.
   *
   * @return the enterprise
   */
  public Enterprise getEnterprise() {
    return enterprise;
  }

  /**
   * Sets enterprise.
   *
   * @param enterpriseParam the enterprise param
   */
  public void setEnterprise(final Enterprise enterpriseParam) {
    enterprise = enterpriseParam;
  }

}
