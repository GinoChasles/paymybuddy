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

/**
 * The type User.
 */
@Entity
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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

  @OneToMany(mappedBy = "user")
  @JsonManagedReference
  private List<Account> account;

  /**
   * The Transactions emit.
   */
  @OneToMany(mappedBy = "emitter")
  @JsonManagedReference
  private List<Transaction> transactionsEmit;

  /**
   * The Transactions receiver.
   */
  @OneToMany(mappedBy = "receiver")
  @JsonManagedReference
  private List<Transaction> transactionsReceiver;

//  @OneToMany(mappedBy = "user")
//  List<Commission> commissions;

  @JsonIgnoreProperties({"friends", "transactionsReceiver", "transactionsEmit", "account", "password", "idUser", "bankAccount", "roles"})
  @ManyToMany
  @JoinTable(name = "user_has_user",
      joinColumns = @JoinColumn(name = "id_user"),
      inverseJoinColumns = @JoinColumn(name = "id_friend"))
      private List<User> friends = new ArrayList<>();

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_role",
      joinColumns = @JoinColumn(name = "id_user"),
      inverseJoinColumns = @JoinColumn(name = "id_role"))
  private List<Role> roles = new ArrayList<>();

  /**
   * Instantiates a new User.
   */
  public User() {
  }

  /**
   * Instantiates a new User.
   *
   * @param idUserParam         the id user param
   * @param usernameParam       the username param
   * @param passwordParam       the password param
   * @param emailParam          the email param
   * @param accountBalanceParam the account balance param
   */
  public User(final int idUserParam, final String usernameParam, final String passwordParam,
              final String emailParam, final double accountBalanceParam) {
    idUser = idUserParam;
    username = usernameParam;
    password = passwordParam;
    email = emailParam;
    accountBalance = accountBalanceParam;
  }

  /**
   * Instantiates a new User.
   *
   * @param usernameParam       the username param
   * @param passwordParam       the password param
   * @param emailParam          the email param
   * @param accountBalanceParam the account balance param
   */
  public User(final String usernameParam, final String passwordParam, final String emailParam,
              final double accountBalanceParam) {
    username = usernameParam;
    password = passwordParam;
    email = emailParam;
    accountBalance = accountBalanceParam;
  }

  /**
   * Gets id user.
   *
   * @return the id user
   */
  public int getIdUser() {
    return idUser;
  }

  /**
   * Sets id user.
   *
   * @param idUserParam the id user param
   */
  public void setIdUser(final int idUserParam) {
    idUser = idUserParam;
  }

  /**
   * Gets username.
   *
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * Sets username.
   *
   * @param usernameParam the username param
   */
  public void setUsername(final String usernameParam) {
    username = usernameParam;
  }

  /**
   * Gets password.
   *
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets password.
   *
   * @param passwordParam the password param
   */
  public void setPassword(final String passwordParam) {
    password = passwordParam;
  }

  /**
   * Gets email.
   *
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets email.
   *
   * @param emailParam the email param
   */
  public void setEmail(final String emailParam) {
    email = emailParam;
  }

  /**
   * Gets account balance.
   *
   * @return the account balance
   */
  public double getAccountBalance() {
    return accountBalance;
  }

  /**
   * Sets account balance.
   *
   * @param accountBalanceParam the account balance param
   */
  public void setAccountBalance(final double accountBalanceParam) {
    accountBalance = accountBalanceParam;
  }

  /**
   * Gets account.
   *
   * @return the account
   */
  public List<Account> getAccount() {
    return account;
  }

  /**
   * Sets account.
   *
   * @param accountParam the account param
   */
  public void setAccount(final List<Account> accountParam) {
    account = accountParam;
  }

  /**
   * Gets transactions emit.
   *
   * @return the transactions emit
   */
  public List<Transaction> getTransactionsEmit() {
    return transactionsEmit;
  }

  /**
   * Sets transactions emit.
   *
   * @param transactionsEmitParam the transactions emit param
   */
  public void setTransactionsEmit(
      final List<Transaction> transactionsEmitParam) {
    transactionsEmit = transactionsEmitParam;
  }

  /**
   * Gets transactions receiver.
   *
   * @return the transactions receiver
   */
  public List<Transaction> getTransactionsReceiver() {
    return transactionsReceiver;
  }

  /**
   * Sets transactions receiver.
   *
   * @param transactionsReceiverParam the transactions receiver param
   */
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

  /**
   * Gets friends.
   *
   * @return the friends
   */
  public List<User> getFriends() {
    return friends;
  }

  /**
   * Sets friends.
   *
   * @param friendsParam the friends param
   */
  public void setFriends(final List<User> friendsParam) {
    friends = friendsParam;
  }

  /**
   * Gets roles.
   *
   * @return the roles
   */
  public List<Role> getRoles() {
    return roles;
  }

  /**
   * Sets roles.
   *
   * @param rolesParam the roles param
   */
  public void setRoles(final List<Role> rolesParam) {
    roles = rolesParam;
  }
}
