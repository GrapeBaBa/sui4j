package io.sui.clients;


/**
 * The type Sui object not found exception.
 */
public class SuiObjectNotFoundException extends RuntimeException {

  /**
   * Instantiates a new Sui object not found exception.
   */
  public SuiObjectNotFoundException() {
    super("sui object not exist.");
  }
}
