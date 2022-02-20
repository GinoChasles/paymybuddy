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

@Entity
public class Transaction {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
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

  public Transaction() {
  }

  public Transaction(final String descriptionParam, final double amountParam) {
    description = descriptionParam;
    amount = amountParam;
  }

  public Transaction(final String descriptionParam, final double amountParam,
                     final User emitterParam, final User receiverParam) {
    description = descriptionParam;
    amount = amountParam;
    emitter = emitterParam;
    receiver = receiverParam;
  }

  public int getIdTransaction() {
    return idTransaction;
  }

  public void setIdTransaction(final int idTransactionParam) {
    idTransaction = idTransactionParam;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(final String descriptionParam) {
    description = descriptionParam;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(final double amountParam) {
    amount = amountParam;
  }

  public User getEmitter() {
    return emitter;
  }

  public void setEmitter(final User emitterParam) {
    emitter = emitterParam;
  }

  public User getReceiver() {
    return receiver;
  }

  public void setReceiver(final User receiverParam) {
    receiver = receiverParam;
  }

  public Commission getCommission() {
    return commission;
  }

  public void setCommission(final Commission commissionParam) {
    commission = commissionParam;
  }

}
