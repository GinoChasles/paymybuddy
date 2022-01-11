package com.gino.paymybuddy.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Transaction {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  @Column(name = "idTransaction", nullable = false)
  private int idTransaction;

  @Column(name = "description")
  @Size(max = 255)
  private String description;

  @Column(name = "amount")
  private double amount;

  @ManyToOne
  @JoinColumn(name = "emitter")
  @NotEmpty(message = "You must select a friend to transfer money")
  private User emitter;

  @ManyToOne
  @JoinColumn(name = "receiver")
  @NotEmpty(message = "You must select a friend to transfer money")
  private User receiver;

  @OneToOne(mappedBy = "transaction")
  private Commission commission;

  @ManyToMany
  @JoinTable(name = "user_has_transaction",
      joinColumns = @JoinColumn(name = "idTransaction"),
      inverseJoinColumns = @JoinColumn(name = "idUser"))
  private List<User> users;
}
