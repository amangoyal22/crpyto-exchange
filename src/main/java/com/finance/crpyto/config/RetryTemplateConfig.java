package com.finance.crpyto.config;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * The type Retry template config.
 */
@Configuration
public class RetryTemplateConfig {
  /**
   * The Connection time out.
   */
  @Value("${retry-template.back-off-period}")
  private String backOffPeriod;
  /**
   * The Read time out.
   */
  @Value("${retry-template.max-rety}")
  private int maxRetry;

  /**
   * Retry template retry template.
   *
   * @return the retry template
   */
  @Bean
  public RetryTemplate retryTemplate() {
    final RetryTemplate retryTemplate = new RetryTemplate();
    final FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
    fixedBackOffPolicy.setBackOffPeriod(Duration.parse(backOffPeriod).toMillis());
    retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
    final SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
    retryPolicy.setMaxAttempts(maxRetry);
    retryTemplate.setRetryPolicy(retryPolicy);
    return retryTemplate;
  }
}
