package com.gino.paymybuddy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class BankAccount {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  @Column(name="idBankAccount", nullable = false)
  private int idBankAccount;

  @Column(name = "iban")
  private int iban;

  @Column(name = "bic")
  private String bic;

  @Column(name = "accountNumber")
  private int accountNumber;

  @Column(name = "key")
  private int key;

  @Column(name = "amount", columnDefinition = "Decimal(10,2)")
  @PositiveOrZero
  private double amount;


  @OneToOne(mappedBy = "bankAccount")
  private User user;

  @OneToOne(mappedBy = "bankAccount")
  private Enterprise enterprise;


  public BankAccount(final int idBankAccountParam, final int ibanParam, final String bicParam,
                     final int accountNumberParam,
                     final int keyParam, final double amountParam) {
    idBankAccount = idBankAccountParam;
    iban = ibanParam;
    bic = bicParam;
    accountNumber = accountNumberParam;
    key = keyParam;
    amount = amountParam;
  }

  public BankAccount() {

  }

  public int getIdBankAccount() {
    return idBankAccount;
  }

  public void setIdBankAccount(final int idBankAccountParam) {
    idBankAccount = idBankAccountParam;
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

  public int getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(final int accountNumberParam) {
    accountNumber = accountNumberParam;
  }

  public int getKey() {
    return key;
  }

  public void setKey(final int keyParam) {
    key = keyParam;
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
