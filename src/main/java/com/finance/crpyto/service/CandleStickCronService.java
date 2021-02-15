package com.finance.crpyto.service;

import com.finance.crpyto.components.CandleStickComponent;
import com.finance.crpyto.components.ExchangeComponent;
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
   * The Exchange task scheduler.
   */
  private final ThreadPoolTaskScheduler exchangeTaskScheduler;
  /**
   * The Exchange symbol update.
   */
  private final PeriodicTrigger exchangeSymbolUpdate;

  /**
   * Execute exchange cron.
   */
  public void executeExchangeCron() {
    candleStickComponent.run();
//    exchangeTaskScheduler.schedule(exchangeComponent,exchangeSymbolUpdate);
  }
}
