package com.finance.crpyto.integration.binance.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * The type Klines details.
 */
@Data
@AllArgsConstructor
@Builder
public class KlinesDetails {

  /**
   * The Open time.
   */
  private long openTime;
  /**
   * The Open.
   */
  private double open;
  /**
   * The High.
   */
  private double high;
  /**
   * The Low.
   */
  private double low;
  /**
   * The Close.
   */
  private double close;
  /**
   * The Volume.
   */
  private double volume;
  /**
   * The Close time.
   */
  private long closeTime;
  /**
   * The Number of trades.
   */
  private int numberOfTrades;
  /**
   * The Taker buy base assest volume.
   */
  private double takerBuyBaseAssestVolume;
  /**
   * The Taker buy quote assest volume.
   */
  private double takerBuyQuoteAssestVolume;
  /**
   * The Quote assest volume.
   */
  private double quoteAssestVolume;
}
