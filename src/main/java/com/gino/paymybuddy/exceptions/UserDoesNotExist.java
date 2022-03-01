package com.gino.paymybuddy.exceptions;

public class UserDoesNotExist extends RuntimeException{
  public UserDoesNotExist(String s) {
    super(s);
  }
}
