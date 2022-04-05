package com.gino.paymybuddy.exceptions;

/**
 * The type User already in friend list.
 */
public class UserAlreadyInFriendList extends RuntimeException {

  /**
   * Instantiates a new User already in friend list.
   *
   * @param s the s
   */
  public UserAlreadyInFriendList(final String s) {
    super(s);
  }
}
