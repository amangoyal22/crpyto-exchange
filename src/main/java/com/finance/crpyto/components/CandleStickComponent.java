package com.finance.crpyto.components;

import com.finance.crpyto.dao.CandleSticksDetailsDao;
import com.finance.crpyto.dao.ExchangeDetailsDao;
import com.finance.crpyto.registries.CandleSticksRegistry;
import com.finance.crpyto.utils.CommonUtils;
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
   * Run.
   */
  @Override
  public void run() {
    try {
      final long time = CommonUtils.getCurrentMinuteMillisUTC();
      log.info("Cron Running For CandleSticks At {}",time);
      exchangeDetailsDao.findAllActive()
          .parallelStream()
          .map(exchangeDetails ->
              CandleSticksRegistry.get(exchangeDetails.getVendorId())
                  .getCandleStickFor1m(time, exchangeDetails.getSymbol()))
          .forEach(candleSticksDetailsDao::save);
      log.info("Cron executed Successfully for the 1m candle Data");
    } catch (final Exception exp) {
      log.error("Error while Updating the candle Data: {}", exp.getMessage());
    }
  }
}
