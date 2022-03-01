package com.gino.paymybuddy.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class AppPropertiesExt {
    private Error error;

    public static class Error {
      private String alreadyInFriendList;
      private String userDoesNotExist;
      private String userAlreadyExist;


      public String getUserAlreadyExist() {
        return userAlreadyExist;
      }

      public void setUserAlreadyExist(final String userAlreadyExistParam) {
        userAlreadyExist = userAlreadyExistParam;
      }

      public String getUserDoesNotExist() {
        return userDoesNotExist;
      }

      public void setUserDoesNotExist(final String userDoesNotExistParam) {
        userDoesNotExist = userDoesNotExistParam;
      }

      public String getAlreadyInFriendList() {
        return alreadyInFriendList;
      }

      public void setAlreadyInFriendList(final String alreadyInFriendListParam) {
        alreadyInFriendList = alreadyInFriendListParam;
      }
    }

  public Error getError() {
    return error;
  }

  public void setError(final Error errorParam) {
    error = errorParam;
  }

}
