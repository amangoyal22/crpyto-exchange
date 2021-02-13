package com.finance.crpyto.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * The type Email config.
 */
@Configuration
@ConfigurationProperties(prefix = "thread-pool")
@Data
public class EmailConfig {

  /**
   * The Api key.
   */
  @Value("${email-service.api-key}")
  private String apiKey;

  /**
   * The Url.
   */
  @Value("${email-service.url}")
  private String url;
}
