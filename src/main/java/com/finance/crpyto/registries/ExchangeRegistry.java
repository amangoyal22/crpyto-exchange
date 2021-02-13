package com.finance.crpyto.registries;

import com.finance.crpyto.constant.ErrorConstantUtils;
import com.finance.crpyto.exceptions.CrpytoException;
import com.finance.crpyto.integration.IExchange;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * The type Exchange registry.
 */
@Slf4j
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExchangeRegistry {

  /**
   * The constant EXCHANGE_MAP.
   */
  private static final Map<String, IExchange> EXCHANGE_MAP =
      new ConcurrentHashMap<>();

  /**
   * Register.
   *
   * @param key   the key
   * @param value the value
   */
  public static void register(final String key, final IExchange value) {
    EXCHANGE_MAP.put(key, value);
  }

  /**
   * Get exchange.
   *
   * @param key the key
   * @return the exchange
   */
  public static IExchange get(final String key) {
    if (!EXCHANGE_MAP.containsKey(key)) {
      throw new CrpytoException(ErrorConstantUtils.EXCHANGE_REGISTRY_UNKNOWN);
    }
    return EXCHANGE_MAP.get(key);
  }
}
