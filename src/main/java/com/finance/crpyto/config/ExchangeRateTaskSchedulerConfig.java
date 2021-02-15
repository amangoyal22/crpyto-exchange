package com.finance.crpyto.config;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;

/**
 * The type Exchange rate task scheduler config.
 */
@Configuration
@ConfigurationProperties(prefix = "crons")
public class ExchangeRateTaskSchedulerConfig {

  /**
   * The Periodic gap.
   */
  @Value("${crons.exhange-symbol.periodic-gap}")
  private String periodicGap;

  /**
   * The Init delay.
   */
  @Value("${crons.exhange-symbol.init-delay}")
  private String initDelay;

  /**
   * Exchange symbol update periodic trigger.
   *
   * @return the periodic trigger
   */
  @Bean
  public PeriodicTrigger exchangeSymbolUpdate() {

    final var periodicTrigger = new PeriodicTrigger(
        Integer.parseInt(String.valueOf(Duration.parse(periodicGap).toMillis())),
        TimeUnit.MILLISECONDS);
    periodicTrigger.setFixedRate(Boolean.TRUE);
    periodicTrigger.setInitialDelay(
        Integer.parseInt(String.valueOf(Duration.parse(initDelay).toMillis())));
    return periodicTrigger;
  }

  /**
   * Exchange task scheduler thread pool task scheduler.
   *
   * @return the thread pool task scheduler
   */
  @Bean
  public ThreadPoolTaskScheduler exchangeTaskScheduler() {
    ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
    threadPoolTaskScheduler.setPoolSize(3);
    threadPoolTaskScheduler.setThreadNamePrefix("ExchangeTaskScheduler");
    return threadPoolTaskScheduler;
  }
}
