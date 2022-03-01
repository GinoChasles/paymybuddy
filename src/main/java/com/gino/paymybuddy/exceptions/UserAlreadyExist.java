package com.gino.paymybuddy.exceptions;

public class UserAlreadyExist extends RuntimeException{
  public UserAlreadyExist(String s) {
    super(s);
  }
}
