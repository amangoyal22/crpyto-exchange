package com.finance.crpyto.integration.binance.enums;

import lombok.Getter;

/**
 * The enum Rate limit enum.
 */
@Getter
public enum  RateLimitEnum {

  /**
   * The Request weight.
   */
  REQUEST_WEIGHT,
  /**
   * The Raw request.
   */
  RAW_REQUESTS,
  /**
   * The Order.
   */
  ORDER,
  /**
   * Orders rate limit enum.
   */
  ORDERS
}
