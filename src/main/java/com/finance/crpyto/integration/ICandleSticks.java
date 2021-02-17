package com.finance.crpyto.integration;

import com.finance.crpyto.dao.CandleSticksDetailsDao;

/**
 * The interface Candle sticks.
 */
public interface ICandleSticks {

  /**
   * Gets candle stick for 1 m.
   *
   * @param time                   the time
   * @param symbol                 the symbol
   * @param candleSticksDetailsDao the candle sticks details dao
   */
  void getCandleStickFor1m(final long time,
                           final String symbol,
                           final CandleSticksDetailsDao candleSticksDetailsDao);

  /**
   * Register.
   */
  void register();
}
