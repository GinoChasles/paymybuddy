package com.gino.paymybuddy.exceptions;

/**
 * The type User does not exist.
 */
public class UserDoesNotExist extends RuntimeException {
  /**
   * Instantiates a new User does not exist.
   *
   * @param s the s
   */
  public UserDoesNotExist(final String s) {
    super(s);
  }
}
