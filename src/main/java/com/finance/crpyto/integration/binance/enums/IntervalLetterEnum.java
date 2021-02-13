package com.finance.crpyto.integration.binance.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The enum Interval letter enum.
 */
@AllArgsConstructor
@Getter
public enum IntervalLetterEnum {
  /**
   * The Second.
   */
  SECOND("S"),
  /**
   * The Minute.
   */
  MINUTE("M"),
  /**
   * The Hour.
   */
  HOUR("H"),
  /**
   * The Day.
   */
  DAY("D");

  /**
   * The Interval.
   */
  private String interval;
}
