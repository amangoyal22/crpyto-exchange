package com.finance.crpyto.integration.binance.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import org.bson.types.Symbol;

/**
 * The type Exchange list response.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeListResponse implements Serializable {
  /**
   * The Timezone.
   */
  @JsonProperty("timezone")
  private String timezone;

  /**
   * The Server time.
   */
  @JsonProperty("serverTime")
  private long serverTime;

  /**
   * The Rate limits.
   */
  @JsonProperty("rateLimits")
  private List<RateLimitDetails> rateLimits;

  /**
   * The Symbols.
   */
  @JsonProperty("symbols")
  private List<SymbolDetails> symbols;
}
