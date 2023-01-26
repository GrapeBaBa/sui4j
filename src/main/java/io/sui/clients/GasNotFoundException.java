package io.sui.clients;


/**
 * The type Gas not found exception.
 */
public class GasNotFoundException extends RuntimeException {

  /**
   * Instantiates a new Gas not found exception.
   */
  public GasNotFoundException() {
    super("gas not exist.");
  }
}
