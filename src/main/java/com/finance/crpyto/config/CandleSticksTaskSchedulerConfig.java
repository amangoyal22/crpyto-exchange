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
 * The type Candle sticks task scheduler config.
 */
@Configuration
@ConfigurationProperties(prefix = "crons")
public class CandleSticksTaskSchedulerConfig {

  /**
   * The Periodic gap.
   */
  @Value("${crons.candlesticks.periodic-gap}")
  private String periodicGap;

  /**
   * The Init delay.
   */
  @Value("${crons.candlesticks.init-delay}")
  private String initDelay;

  /**
   * Candlesticks symbol update periodic trigger.
   *
   * @return the periodic trigger
   */
  @Bean
  public PeriodicTrigger candlesticksUpdate() {

    final var periodicTrigger = new PeriodicTrigger(
        Integer.parseInt(String.valueOf(Duration.parse(periodicGap).toMillis())),
        TimeUnit.MILLISECONDS);
    periodicTrigger.setFixedRate(Boolean.TRUE);
    periodicTrigger.setInitialDelay(
        Integer.parseInt(String.valueOf(Duration.parse(initDelay).toMillis())));
    return periodicTrigger;
  }

  /**
   * Candlesticks task scheduler thread pool task scheduler.
   *
   * @return the thread pool task scheduler
   */
  @Bean
  public ThreadPoolTaskScheduler candlesticksTaskScheduler() {
    ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
    threadPoolTaskScheduler.setPoolSize(25);
    threadPoolTaskScheduler.setThreadNamePrefix("CandlesticksTaskScheduler");
    return threadPoolTaskScheduler;
  }
}
