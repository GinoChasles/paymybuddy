package com.gino.paymybuddy.dto;

/**
 * The type Transaction dto.
 */
public class TransactionDTO {
  private String connection;
  private String description;
  private double amount;

  /**
   * Instantiates a new Transaction dto.
   *
   * @param connectionParam  the connection param
   * @param descriptionParam the description param
   * @param amountParam      the amount param
   */
  public TransactionDTO(final String connectionParam, final String descriptionParam,
                        final double amountParam) {
    connection = connectionParam;
    description = descriptionParam;
    amount = amountParam;
  }

  /**
   * Instantiates a new Transaction dto.
   */
  public TransactionDTO() {
  }

  /**
   * Gets connection.
   *
   * @return the connection
   */
  public String getConnection() {
    return connection;
  }

  /**
   * Sets connection.
   *
   * @param connectionParam the connection param
   */
  public void setConnection(final String connectionParam) {
    connection = connectionParam;
  }

  /**
   * Gets description.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets description.
   *
   * @param descriptionParam the description param
   */
  public void setDescription(final String descriptionParam) {
    description = descriptionParam;
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
}
