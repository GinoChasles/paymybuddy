package com.gino.paymybuddy.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Enterprise {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int idEnterprise;

  @Column(name = "name")
  private String name;

  @OneToOne
  @JoinColumn(name = "idBankAccount", referencedColumnName = "idBankAccount", nullable = false)
  private BankAccount bankAccount;

  @OneToMany(mappedBy = "enterprise")
  private List<Commission> commission;
}
