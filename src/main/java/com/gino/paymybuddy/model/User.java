package com.gino.paymybuddy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Column(name = "id_user", nullable = false)
  private int idUser;

  @Column(name = "username", nullable = false)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "email", nullable = false, unique = true)
  @Email
  @NotEmpty(message = "Please enter the mail")
  private String email;

  @Column(name = "account", columnDefinition = "Decimal(10,2)")
  @PositiveOrZero
  private double accountBalance;

  @OneToOne(mappedBy = "user")
  private Account account;

  @OneToMany(mappedBy = "emitter")
  @JsonManagedReference
  List<Transaction> transactionsEmit;

  @OneToMany(mappedBy = "receiver")
  @JsonManagedReference
  List<Transaction> transactionsReceiver;

//  @OneToMany(mappedBy = "user")
//  List<Commission> commissions;

  @JsonIgnoreProperties({"friends", "transactionsReceiver", "transactionsEmit", "account", "password", "idUser", "bankAccount", "roles"})
  @ManyToMany
  @JoinTable(name="user_has_user",
      joinColumns=@JoinColumn(name="id_user"),
      inverseJoinColumns=@JoinColumn(name="id_friend"))
      private List<User> friends = new ArrayList<>();

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_role",
      joinColumns = @JoinColumn(name = "id_user"),
      inverseJoinColumns = @JoinColumn(name = "id_role"))
  private List<Role> roles = new ArrayList<>();

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

  public Account getBankAccount() {
    return account;
  }

  public void setBankAccount(final Account accountParam) {
    account = accountParam;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(final Account accountParam) {
    account = accountParam;
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

  //  public List<Commission> getCommissions() {
//    return commissions;
//  }
//
//  public void setCommissions(final List<Commission> commissionsParam) {
//    commissions = commissionsParam;
//  }

  public List<User> getFriends() {
    return friends;
  }

  public void setFriends(final List<User> friendsParam) {
    friends = friendsParam;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(final List<Role> rolesParam) {
    roles = rolesParam;
  }
}
