package com.gino.paymybuddy.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.PositiveOrZero;

/**
 * The type Account.
 */
@Entity
@Table(name = "account")
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_account", nullable = false)
  private int idAccount;

  @Column(name = "iban")
  private int iban;

  @Column(name = "bic")
  private String bic;

  @Column(name = "accountnumber")
  private String accountnumber;

  @Column(name = "amount", columnDefinition = "Decimal(10,2)")
  @PositiveOrZero
  private double amount;


  @ManyToOne
  @JsonBackReference
  @JoinColumn(name = "id_user")
  private User user;

  @OneToOne
  @JoinColumn(name = "id_enterprise")
  private Enterprise enterprise;


  /**
   * Instantiates a new Account.
   *
   * @param idBankaccountParam the id bankaccount param
   * @param ibanParam          the iban param
   * @param bicParam           the bic param
   * @param accountnumberParam the accountnumber param
   * @param amountParam        the amount param
   */
  public Account(final int idBankaccountParam, final int ibanParam, final String bicParam,
                 final String accountnumberParam, final double amountParam) {
    idAccount = idBankaccountParam;
    iban = ibanParam;
    bic = bicParam;
    accountnumber = accountnumberParam;
    amount = amountParam;
  }

  /**
   * Instantiates a new Account.
   *
   * @param ibanParam          the iban param
   * @param bicParam           the bic param
   * @param accountnumberParam the accountnumber param
   * @param amountParam        the amount param
   */
  public Account(final int ibanParam, final String bicParam, final String accountnumberParam,
                 final double amountParam) {
    iban = ibanParam;
    bic = bicParam;
    accountnumber = accountnumberParam;
    amount = amountParam;
  }

  /**
   * Instantiates a new Account.
   */
  public Account() {

  }

  /**
   * Gets id account.
   *
   * @return the id account
   */
  public int getIdAccount() {
    return idAccount;
  }

  /**
   * Sets id account.
   *
   * @param idAccountParam the id account param
   */
  public void setIdAccount(final int idAccountParam) {
    idAccount = idAccountParam;
  }

  /**
   * Gets iban.
   *
   * @return the iban
   */
  public int getIban() {
    return iban;
  }

  /**
   * Sets iban.
   *
   * @param ibanParam the iban param
   */
  public void setIban(final int ibanParam) {
    iban = ibanParam;
  }

  /**
   * Gets bic.
   *
   * @return the bic
   */
  public String getBic() {
    return bic;
  }

  /**
   * Sets bic.
   *
   * @param bicParam the bic param
   */
  public void setBic(final String bicParam) {
    bic = bicParam;
  }

  /**
   * Gets accountnumber.
   *
   * @return the accountnumber
   */
  public String getAccountnumber() {
    return accountnumber;
  }

  /**
   * Sets accountnumber.
   *
   * @param accountNumberParam the account number param
   */
  public void setAccountnumber(final String accountNumberParam) {
    accountnumber = accountNumberParam;
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
   * Gets user.
   *
   * @return the user
   */
  public User getUser() {
    return user;
  }

  /**
   * Sets user.
   *
   * @param userParam the user param
   */
  public void setUser(final User userParam) {
    user = userParam;
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

  @Override
  public String toString() {
    return "Account{" +
        "idAccount=" + idAccount +
        ", iban=" + iban +
        ", bic='" + bic + '\'' +
        ", accountnumber='" + accountnumber + '\'' +
        ", amount=" + amount +
        ", user=" + user +
        ", enterprise=" + enterprise +
        '}';
  }
}
