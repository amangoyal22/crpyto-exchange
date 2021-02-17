package com.finance.crpyto.service;

import com.finance.crpyto.components.ExchangeComponent;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Service;

/**
 * The type Exchange cron service.
 */
@Service
@AllArgsConstructor
public class ExchangeCronService {

  /**
   * The Populate component.
   */
  private final ExchangeComponent exchangeComponent;
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
    exchangeTaskScheduler.schedule(exchangeComponent,exchangeSymbolUpdate);
  }
}
