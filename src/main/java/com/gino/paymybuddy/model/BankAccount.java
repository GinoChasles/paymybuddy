package com.gino.paymybuddy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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

  @OneToOne(mappedBy = "bankAccount")
  private User user;

  @OneToOne(mappedBy = "bankAccount")
  private Enterprise enterprise;

}
