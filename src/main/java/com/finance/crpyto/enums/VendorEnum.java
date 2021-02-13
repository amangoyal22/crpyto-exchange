package com.finance.crpyto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The enum Vendor enum.
 */
@AllArgsConstructor
@Getter
public enum  VendorEnum {
  /**
   * Binance vendor enum.
   */
  BINANCE("6027a174cc7068160e8a207e");

  /**
   * The Id.
   */
  private String id;
}
