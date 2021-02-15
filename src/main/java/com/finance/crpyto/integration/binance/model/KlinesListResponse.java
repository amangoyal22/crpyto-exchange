package com.finance.crpyto.integration.binance.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * The type Klines list response.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class KlinesListResponse implements Serializable {

  /**
   * The Candle.
   */
  List<List<Object>> candle;
}
