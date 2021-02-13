package com.finance.crpyto.integration.binance.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.finance.crpyto.integration.binance.enums.StatusEnum;
import java.io.Serializable;
import lombok.Data;

/**
 * The type Symbol details.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SymbolDetail implements Serializable {

  /**
   * The Symbol.
   */
  @JsonProperty("symbol")
  private String symbol;

  /**
   * The Status.
   */
  @JsonProperty("status")
  private StatusEnum status;

  /**
   * The Base asset.
   */
  @JsonProperty("baseAsset")
  private String baseAsset;

  /**
   * The Base asset precision.
   */
  @JsonProperty("baseAssetPrecision")
  private int baseAssetPrecision;

  /**
   * The Base commission precision.
   */
  @JsonProperty("baseCommissionPrecision")
  private int baseCommissionPrecision;

  /**
   * The Quote asset.
   */
  @JsonProperty("quoteAsset")
  private String quoteAsset;

  /**
   * The Quote precision.
   */
  @JsonProperty("quotePrecision")
  private int quotePrecision;
  /**
   * The Quote asset precision.
   */
  @JsonProperty("quoteAssetPrecision")
  private int quoteAssetPrecision;
  /**
   * The Quote commission precision.
   */
  @JsonProperty("quoteCommissionPrecision")
  private int quoteCommissionPrecision;
}
