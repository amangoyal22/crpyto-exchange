package com.finance.crpyto.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Executor config.
 */
@Configuration
@ConfigurationProperties(prefix = "thread-pool")
public class ExecutorConfig {

  /**
   * The Count.
   */
  @Value("${thread-pool.count}")
  private int count;

  /**
   * Executor service executor service.
   *
   * @return the executor service
   */
  @Bean
  public ExecutorService executorService() {
    return Executors.newFixedThreadPool(count);
  }
}
