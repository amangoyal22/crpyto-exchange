package com.finance.crpyto.integration.binance.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * The enum Status enum.
 */
@AllArgsConstructor
@Getter
public enum StatusEnum {
  /**
   * Pre trading status enum.
   */
  PRE_TRADING(true),
  /**
   * Trading status enum.
   */
  TRADING(true),
  /**
   * Post trading status enum.
   */
  POST_TRADING(true),
  /**
   * End of day status enum.
   */
  END_OF_DAY(true),
  /**
   * Halt status enum.
   */
  HALT(true),
  /**
   * Auction match status enum.
   */
  AUCTION_MATCH(true),
  /**
   * Break status enum.
   */
  BREAK(true);
  /**
   * The Active.
   */
  private boolean active;
}
