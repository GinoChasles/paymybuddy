package com.gino.paymybuddy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "account")
public class Account {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Column(name="id_account", nullable = false)
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


  @OneToOne
  @JoinColumn(name = "id_user", nullable = false)
  private User user;

  @OneToOne
  @JoinColumn(name = "id_enterprise", nullable = false)
  private Enterprise enterprise;


  public Account(final int idBankaccountParam, final int ibanParam, final String bicParam,
                 final String accountnumberParam, final double amountParam) {
    idAccount = idBankaccountParam;
    iban = ibanParam;
    bic = bicParam;
    accountnumber = accountnumberParam;
    amount = amountParam;
  }

  public Account() {

  }

  public int getIdBankaccount() {
    return idAccount;
  }

  public void setIdBankaccount(final int idBankAccountParam) {
    idAccount = idBankAccountParam;
  }

  public int getIban() {
    return iban;
  }

  public void setIban(final int ibanParam) {
    iban = ibanParam;
  }

  public String getBic() {
    return bic;
  }

  public void setBic(final String bicParam) {
    bic = bicParam;
  }

  public String getAccountnumber() {
    return accountnumber;
  }

  public void setAccountnumber(final String accountNumberParam) {
    accountnumber = accountNumberParam;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(final double amountParam) {
    amount = amountParam;
  }


  public User getUser() {
    return user;
  }

  public void setUser(final User userParam) {
    user = userParam;
  }

  public Enterprise getEnterprise() {
    return enterprise;
  }

  public void setEnterprise(final Enterprise enterpriseParam) {
    enterprise = enterpriseParam;
  }
}
