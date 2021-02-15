package com.finance.crpyto.integration.binance.constant;

/**
 * The interface Parameter constant utils.
 */
public interface ParameterConstantUtils {

  /**
   * The constant INTERVAL.
   */
  String INTERVAL = "interval";
  /**
   * The constant INTERVAL_VALUE_1M.
   */
  String INTERVAL_VALUE_1M = "1m";
  /**
   * The constant SYMBOL.
   */
  String SYMBOL = "symbol";
  /**
   * The constant START_TIME.
   */
  String START_TIME = "startTime";
  /**
   * The constant END_TIME.
   */
  String END_TIME = "endTime";
  /**
   * The constant LIMIT.
   */
  String LIMIT = "limit";
  /**
   * The constant LIMIT_VALUE.
   */
  String LIMIT_VALUE = "1";

  /**
   * The interface Klines.
   */
  interface Klines {
    /**
     * The constant OPEN_TIME.
     */
    int OPEN_TIME = 0;
    /**
     * The constant OPEN.
     */
    int OPEN = 1;
    /**
     * The constant HIGH.
     */
    int HIGH = 2;
    /**
     * The constant LOW.
     */
    int LOW = 3;
    /**
     * The constant CLOSE.
     */
    int CLOSE = 4;
    /**
     * The constant VOLUME.
     */
    int VOLUME = 5;
    /**
     * The constant CLOSE_TIME.
     */
    int CLOSE_TIME = 6;
    /**
     * The constant QUOTE_VOL.
     */
    int QUOTE_VOL = 7;
    /**
     * The constant NO_OF_TRADES.
     */
    int NO_OF_TRADES = 8;
    /**
     * The constant BUY_BASE_ASSEST_VOLUME.
     */
    int BUY_BASE_ASSEST_VOLUME = 9;
    /**
     * The constant BUY_QUOTE_ASSEST_VOLUME.
     */
    int BUY_QUOTE_ASSEST_VOLUME = 10;
    /**
     * The constant IGNORE.
     */
    int IGNORE = 11;
  }
}
