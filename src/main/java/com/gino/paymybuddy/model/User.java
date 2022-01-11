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
import javax.validation.constraints.PositiveOrZero;

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

  @Column(name = "accountBalance", columnDefinition = "Decimal(10,2)")
  @PositiveOrZero
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
      inverseJoinColumns=@JoinColumn(name="idUserFriend"))
      private List<User> friends;

  @ManyToMany
  @JoinTable(name = "user_has_transaction",
  joinColumns = @JoinColumn(name = "idUser"),
  inverseJoinColumns = @JoinColumn(name = "idTransaction"))
  private List<Transaction> transactions;


  public User() {
  }

  public User(final String usernameParam, final String passwordParam, final String emailParam,
              final double accountBalanceParam) {
    username = usernameParam;
    password = passwordParam;
    email = emailParam;
    accountBalance = accountBalanceParam;
  }

  public int getIdUser() {
    return idUser;
  }

  public void setIdUser(final int idUserParam) {
    idUser = idUserParam;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(final String usernameParam) {
    username = usernameParam;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(final String passwordParam) {
    password = passwordParam;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(final String emailParam) {
    email = emailParam;
  }

  public double getAccountBalance() {
    return accountBalance;
  }

  public void setAccountBalance(final double accountBalanceParam) {
    accountBalance = accountBalanceParam;
  }

  public BankAccount getBankAccount() {
    return bankAccount;
  }

  public void setBankAccount(final BankAccount bankAccountParam) {
    bankAccount = bankAccountParam;
  }

  public List<Transaction> getTransactionsEmit() {
    return transactionsEmit;
  }

  public void setTransactionsEmit(
      final List<Transaction> transactionsEmitParam) {
    transactionsEmit = transactionsEmitParam;
  }

  public List<Transaction> getTransactionsReceiver() {
    return transactionsReceiver;
  }

  public void setTransactionsReceiver(
      final List<Transaction> transactionsReceiverParam) {
    transactionsReceiver = transactionsReceiverParam;
  }

  public List<Commission> getCommissions() {
    return commissions;
  }

  public void setCommissions(final List<Commission> commissionsParam) {
    commissions = commissionsParam;
  }

  public List<User> getFriends() {
    return friends;
  }

  public void setFriends(final List<User> friendsParam) {
    friends = friendsParam;
  }

  public List<Transaction> getTransactions() {
    return transactions;
  }

  public void setTransactions(final List<Transaction> transactionsParam) {
    transactions = transactionsParam;
  }
}
