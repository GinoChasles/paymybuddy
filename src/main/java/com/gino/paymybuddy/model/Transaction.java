package com.gino.paymybuddy.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

/**
 * The type Transaction.
 */
@Entity
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_transaction", nullable = false)
  private int idTransaction;

  @Column(name = "description")
  @Size(max = 255)
  private String description;

  @Column(name = "amount", columnDefinition = "Decimal(10,2)")
  @PositiveOrZero
  private double amount;

  @ManyToOne
  @JsonBackReference
  @JoinColumn(name = "id_emitter")
//  @NotEmpty(message = "You must select a friend to transfer money")
  private User emitter;

  @ManyToOne
   @JsonBackReference
  @JoinColumn(name = "id_receiver")
//  @NotEmpty(message = "You must select a friend to transfer money")
  private User receiver;

  @JsonBackReference
  @OneToOne(mappedBy = "transaction")
  private Commission commission;

  /**
   * Instantiates a new Transaction.
   */
  public Transaction() {
  }

  /**
   * Instantiates a new Transaction.
   *
   * @param descriptionParam the description param
   * @param amountParam      the amount param
   */
  public Transaction(final String descriptionParam, final double amountParam) {
    description = descriptionParam;
    amount = amountParam;
  }

  /**
   * Instantiates a new Transaction.
   *
   * @param descriptionParam the description param
   * @param amountParam      the amount param
   * @param emitterParam     the emitter param
   * @param receiverParam    the receiver param
   */
  public Transaction(final String descriptionParam, final double amountParam,
                     final User emitterParam, final User receiverParam) {
    description = descriptionParam;
    amount = amountParam;
    emitter = emitterParam;
    receiver = receiverParam;
  }

  /**
   * Gets id transaction.
   *
   * @return the id transaction
   */
  public int getIdTransaction() {
    return idTransaction;
  }

  /**
   * Sets id transaction.
   *
   * @param idTransactionParam the id transaction param
   */
  public void setIdTransaction(final int idTransactionParam) {
    idTransaction = idTransactionParam;
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

  /**
   * Gets amount.
   *
   * @return the amount
   */
  public double getAmount() {
    return amount;
  }

  /**
   * Sets amount.
   *
   * @param amountParam the amount param
   */
  public void setAmount(final double amountParam) {
    amount = amountParam;
  }

  /**
   * Gets emitter.
   *
   * @return the emitter
   */
  public User getEmitter() {
    return emitter;
  }

  /**
   * Sets emitter.
   *
   * @param emitterParam the emitter param
   */
  public void setEmitter(final User emitterParam) {
    emitter = emitterParam;
  }

  /**
   * Gets receiver.
   *
   * @return the receiver
   */
  public User getReceiver() {
    return receiver;
  }

  /**
   * Sets receiver.
   *
   * @param receiverParam the receiver param
   */
  public void setReceiver(final User receiverParam) {
    receiver = receiverParam;
  }

  /**
   * Gets commission.
   *
   * @return the commission
   */
  public Commission getCommission() {
    return commission;
  }

  /**
   * Sets commission.
   *
   * @param commissionParam the commission param
   */
  public void setCommission(final Commission commissionParam) {
    commission = commissionParam;
  }

}
