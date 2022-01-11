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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table()
public class User {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  @Column(name = "idUser", nullable = false)
  private int idUser;

  @Column(name = "username", nullable = false)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "email", nullable = false, unique = true)
  @Email
  @NotEmpty(message = "Please enter the mail")
  private String email;

  @Column(name = "accountBalance")
  private double accountBalance;

  @OneToOne
  @JoinColumn(name = "idBankAccount", nullable = false)
  private BankAccount bankAccount;

  @OneToMany(mappedBy = "emitter")
  List<Transaction> transactionsEmit;

  @OneToMany(mappedBy = "receiver")
  List<Transaction> transactionsReceiver;

  @OneToMany(mappedBy = "user")
  List<Commission> commissions;

  @ManyToMany
  @JoinTable(name="user_has_user",
      joinColumns=@JoinColumn(name="idUser"),
      inverseJoinColumns=@JoinColumn(name="idUserBis"))
      private List<User> friends;

  @ManyToMany
  @JoinTable(name = "user_has_transaction",
  joinColumns = @JoinColumn(name = "idUser"),
  inverseJoinColumns = @JoinColumn(name = "idTransaction"))
  private List<Transaction> transactions;

}
