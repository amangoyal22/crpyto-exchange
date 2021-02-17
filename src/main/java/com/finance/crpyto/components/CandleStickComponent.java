package com.finance.crpyto.components;

import com.finance.crpyto.constant.ConfigConstantUtils;
import com.finance.crpyto.constant.DataAggregationConstantUtils;
import com.finance.crpyto.dao.CandleSticksDetailsDao;
import com.finance.crpyto.dao.ExchangeDetailsDao;
import com.finance.crpyto.model.repo.ExchangeDetails;
import com.finance.crpyto.registries.CandleSticksRegistry;
import com.finance.crpyto.utils.CommonUtils;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * The type Candle stick component.
 */
@Component
@AllArgsConstructor
@Slf4j
public class CandleStickComponent implements Runnable {

  /**
   * The Exchange details dao.
   */
  private final ExchangeDetailsDao exchangeDetailsDao;
  /**
   * The Candle sticks details dao.
   */
  private final CandleSticksDetailsDao candleSticksDetailsDao;
  /**
   * The Candlesticks executor service.
   */
  private final ExecutorService candlesticksExecutorService;
  /**
   * The Data aggregation component.
   */
  private final DataAggregationComponent dataAggregationComponent;

  /**
   * Run.
   */
  @Override
  public void run() {
    try {
      final long time = CommonUtils.getCurrentMinuteMillisUTC();
      log.info("Cron Running For CandleSticks At {}", time);
      final AtomicInteger count = new AtomicInteger();
      final var listofExchange = exchangeDetailsDao.findAllActive();
      Collections.shuffle(listofExchange);
      listofExchange
          .forEach(exchangeDetails -> action(exchangeDetails, time, count));
      log.info("Cron executed Successfully for the 1m candle Data");
      fiveMinutes(time);
    } catch (final Exception exp) {
      log.error("Error while Updating the candle Data: {}", exp.getMessage());
    }
  }

  /**
   * Action.
   *
   * @param exchangeDetails the exchange details
   * @param time            the time
   * @param count           the count
   */
  private void action(final ExchangeDetails exchangeDetails,
                      final long time,
                      final AtomicInteger count) {
    try {
      CompletableFuture.runAsync(() -> CandleSticksRegistry.get(exchangeDetails.getVendorId())
          .getCandleStickFor1m(
              time,
              exchangeDetails.getSymbol(),
              candleSticksDetailsDao), candlesticksExecutorService);
      count.getAndIncrement();
      if (count.get() > 100) {
        count.set(0);
        Thread.sleep(3000);
      }
    } catch (Exception exp) {
      log.error("Error while Updating the candle Data: {} {}", time, exchangeDetails.getSymbol());
    }
  }

  /**
   * Five minutes.
   *
   * @param time the time
   */
  private void fiveMinutes(final long time) {
    if (CommonUtils.getUTCMinutes(time) % DataAggregationConstantUtils.MIN_5
        == ConfigConstantUtils.DEFAULT_INTEGER) {
      log.info("Five min executed");
      CompletableFuture.runAsync(() -> dataAggregationComponent.runForFiveMins(time));
    }
  }
}
