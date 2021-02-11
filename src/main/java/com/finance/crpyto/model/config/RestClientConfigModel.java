package com.finance.crpyto.model.config;

import lombok.Data;

/**
 * The type Rest client config model.
 */
@Data
public class RestClientConfigModel {
  /**
   * The Connection time out.
   */
  private String connectionTimeOut;
  /**
   * The Read time out.
   */
  private String readTimeOut;
}
