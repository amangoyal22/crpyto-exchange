package com.finance.crpyto.service;

import com.finance.crpyto.components.PopulateComponent;
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
  private final PopulateComponent populateComponent;
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
  @PostConstruct
  public void executeExchangeCron() {
    exchangeTaskScheduler.schedule(populateComponent,exchangeSymbolUpdate);
  }
}
