package com.gino.paymybuddy.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Commission {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int idCommission;

  @Column(name = "pourcentage")
  private double pourcentage;

  @OneToOne
  @JoinColumn(name = "idTransaction", referencedColumnName = "idTransaction")
  private Transaction transaction;

  @ManyToOne
  @JoinColumn(name = "idEnterprise")
  private Enterprise enterprise;

  @ManyToOne
  @JoinColumn(name = "user")
  private User user;

}
