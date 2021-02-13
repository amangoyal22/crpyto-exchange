package com.finance.crpyto.exceptions;

/**
 * The type Crpyto exception.
 */
public class CrpytoException extends RuntimeException {
  /**
   * Instantiates a new Crpyto exception.
   *
   * @param message the message
   */
  public CrpytoException(final String message) {
    super(message);
  }

  /**
   * Instantiates a new Crpyto exception.
   *
   * @param message the message
   * @param cause   the cause
   */
  public CrpytoException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
