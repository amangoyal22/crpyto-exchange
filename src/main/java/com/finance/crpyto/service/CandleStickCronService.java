package com.finance.crpyto.service;

import com.finance.crpyto.components.CandleStickComponent;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Service;

/**
 * The type Candle stick cron service.
 */
@Service
@AllArgsConstructor
public class CandleStickCronService {

  /**
   * The Candle stick component.
   */
  private final CandleStickComponent candleStickComponent;
  /**
   * The Candlesticks task scheduler.
   */
  private final ThreadPoolTaskScheduler candlesticksTaskScheduler;
  /**
   * The Candlesticks update.
   */
  private final PeriodicTrigger candlesticksUpdate;

  /**
   * Execute exchange cron.
   */
  @PostConstruct
  public void executeExchangeCron() {
    candlesticksTaskScheduler.schedule(candleStickComponent, candlesticksUpdate);
  }
}
