package com.finance.crpyto.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * The type Common utils.
 */
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public final class CommonUtils {

  /**
   * Gets past date.
   *
   * @param days the days
   * @return the past date
   */
  public static Date getPastDate(final int days) {
    return Date.from(
        Instant.now().minus(
            days, ChronoUnit.DAYS));
  }
}
