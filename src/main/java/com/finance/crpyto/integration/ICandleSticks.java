package com.finance.crpyto.integration;

import com.finance.crpyto.model.repo.CandleStickDetails;

/**
 * The interface Candle sticks.
 */
public interface ICandleSticks {

  /**
   * Gets candle stick for 1 m.
   *
   * @param time   the time
   * @param symbol the symbol
   * @return the candle stick for 1 m
   */
  CandleStickDetails getCandleStickFor1m(final long time, final String symbol);

  /**
   * Register.
   */
  void register();
}
