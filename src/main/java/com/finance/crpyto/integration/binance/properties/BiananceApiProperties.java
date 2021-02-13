package com.finance.crpyto.integration.binance.properties;

import com.finance.crpyto.integration.models.SubPropertyModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * The type Bianance api properties.
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "vendors.binance")
public class BiananceApiProperties {

  /**
   * The Scheme.
   */
  private String scheme;
  /**
   * The Host.
   */
  private String host;
  /**
   * The Path segment.
   */
  private String pathSegment;
  /**
   * The Exchange list.
   */
  private SubPropertyModel exchangeList;
}
