package com.finance.crpyto.integration.binance.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.finance.crpyto.integration.binance.enums.IntervalLetterEnum;
import com.finance.crpyto.integration.binance.enums.RateLimitEnum;
import java.io.Serializable;
import lombok.Data;

/**
 * The type Rate limit details.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RateLimitDetails implements Serializable {

  /**
   * The Rate limit type.
   */
  @JsonProperty("rateLimitType")
  private RateLimitEnum rateLimitType;

  /**
   * The Interval.
   */
  @JsonProperty("interval")
  private IntervalLetterEnum interval;

  /**
   * The Interval num.
   */
  @JsonProperty("intervalNum")
  private int intervalNum;

  /**
   * The Limit.
   */
  @JsonProperty("limit")
  private int limit;
}
