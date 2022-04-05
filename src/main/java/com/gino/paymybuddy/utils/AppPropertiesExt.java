package com.gino.paymybuddy.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * The type App properties ext.
 */
@ConfigurationProperties(prefix = "app")
public class AppPropertiesExt {
    private Error error;

  /**
   * The type Error.
   */
  public static class Error {
      private String alreadyInFriendList;
      private String userDoesNotExist;
      private String userAlreadyExist;


    /**
     * Gets user already exist.
     *
     * @return the user already exist
     */
    public String getUserAlreadyExist() {
        return userAlreadyExist;
      }

    /**
     * Sets user already exist.
     *
     * @param userAlreadyExistParam the user already exist param
     */
    public void setUserAlreadyExist(final String userAlreadyExistParam) {
        userAlreadyExist = userAlreadyExistParam;
      }

    /**
     * Gets user does not exist.
     *
     * @return the user does not exist
     */
    public String getUserDoesNotExist() {
        return userDoesNotExist;
      }

    /**
     * Sets user does not exist.
     *
     * @param userDoesNotExistParam the user does not exist param
     */
    public void setUserDoesNotExist(final String userDoesNotExistParam) {
        userDoesNotExist = userDoesNotExistParam;
      }

    /**
     * Gets already in friend list.
     *
     * @return the already in friend list
     */
    public String getAlreadyInFriendList() {
        return alreadyInFriendList;
      }

    /**
     * Sets already in friend list.
     *
     * @param alreadyInFriendListParam the already in friend list param
     */
    public void setAlreadyInFriendList(final String alreadyInFriendListParam) {
        alreadyInFriendList = alreadyInFriendListParam;
      }
    }

  /**
   * Gets error.
   *
   * @return the error
   */
  public Error getError() {
    return error;
  }

  /**
   * Sets error.
   *
   * @param errorParam the error param
   */
  public void setError(final Error errorParam) {
    error = errorParam;
  }

}
