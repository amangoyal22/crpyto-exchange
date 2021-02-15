package com.finance.crpyto.registries;

import com.finance.crpyto.constant.ErrorConstantUtils;
import com.finance.crpyto.exceptions.CrpytoException;
import com.finance.crpyto.integration.ICandleSticks;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * The type Candle sticks registry.
 */
@Slf4j
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class CandleSticksRegistry {

  /**
   * The constant CANDLE_STICKS_MAP.
   */
  private static final Map<String, ICandleSticks> CANDLE_STICKS_MAP =
      new ConcurrentHashMap<>();

  /**
   * Register.
   *
   * @param key   the key
   * @param value the value
   */
  public static void register(final String key, final ICandleSticks value) {
    CANDLE_STICKS_MAP.put(key, value);
  }

  /**
   * Get candle sticks.
   *
   * @param key the key
   * @return the candle sticks
   */
  public static ICandleSticks get(final String key) {
    if (!CANDLE_STICKS_MAP.containsKey(key)) {
      throw new CrpytoException(ErrorConstantUtils.CANDLESTICKS_REGISTRY_UNKNOWN);
    }
    return CANDLE_STICKS_MAP.get(key);
  }
}
