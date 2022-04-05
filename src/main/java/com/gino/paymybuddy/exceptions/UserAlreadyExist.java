package com.gino.paymybuddy.exceptions;

/**
 * The type User already exist.
 */
public class UserAlreadyExist extends RuntimeException {
  /**
   * Instantiates a new User already exist.
   *
   * @param s the s
   */
  public UserAlreadyExist(final String s) {
    super(s);
  }
}
