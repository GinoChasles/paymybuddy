package com.gino.paymybuddy.dto;

public class TransactionDTO {
  private String connection;
  private String description;
  private double amount;

  public TransactionDTO(final String connectionParam, final String descriptionParam,
                        final double amountParam) {
    connection = connectionParam;
    description = descriptionParam;
    amount = amountParam;
  }

  public TransactionDTO() {
  }

  public String getConnection() {
    return connection;
  }

  public void setConnection(final String connectionParam) {
    connection = connectionParam;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(final String descriptionParam) {
    description = descriptionParam;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(final double amountParam) {
    amount = amountParam;
  }
}
