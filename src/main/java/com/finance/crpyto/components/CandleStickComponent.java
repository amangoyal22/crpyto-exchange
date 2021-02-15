package com.finance.crpyto.components;

import com.finance.crpyto.dao.CandleSticksDetailsDao;
import com.finance.crpyto.dao.ExchangeDetailsDao;
import com.finance.crpyto.enums.RepoEnum;
import com.finance.crpyto.model.repo.CandleStickDetails;
import com.finance.crpyto.model.repo.ExchangeDetails;
import com.finance.crpyto.registries.CandleSticksRegistry;
import com.finance.crpyto.utils.CommonUtils;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
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

      exchangeDetailsDao.findAllActive()
          .stream()
          .map(exchangeDetails ->
              CandleSticksRegistry.get(exchangeDetails.getVendorId())
                  .getCandleStickFor1m(new Date().getTime(),exchangeDetails.getSymbol()))
          .forEach(candleSticksDetailsDao::save);
      log.info("Cron executed Successfully for the 1m candle Data");
    } catch (final Exception exp) {
      log.error("Error while Updating the candle Data: {}", exp.getMessage());
    }
  }
}
