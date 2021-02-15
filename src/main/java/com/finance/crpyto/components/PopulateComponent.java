package com.finance.crpyto.components;

import com.finance.crpyto.dao.ExchangeDetailsDao;
import com.finance.crpyto.dao.VendorDetailsDao;
import com.finance.crpyto.enums.RepoEnum;
import com.finance.crpyto.model.repo.ExchangeDetails;
import com.finance.crpyto.registries.ExchangeRegistry;
import com.finance.crpyto.utils.CommonUtils;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * The type Populate component.
 */
@Component
@AllArgsConstructor
@Slf4j
public class PopulateComponent implements Runnable {

  /**
   * The Vendor details dao.
   */
  private final VendorDetailsDao vendorDetailsDao;
  /**
   * The Exchange details dao.
   */
  private final ExchangeDetailsDao exchangeDetailsDao;

  /**
   * Run.
   */
  @Override
  public void run() {
    try {
      vendorDetailsDao.findAllActive()
          .stream()
          .map(vendorDetails -> ExchangeRegistry.get(vendorDetails.getId()).getExchangeDetails())
          .collect(Collectors.toList()).stream()
          .flatMap(List::stream)
          .parallel()
          .forEach(this::addOrUpdateExchange);
      log.info("Added and Updated the exchange details successfully");
      deactivateExpiredExchange();
      log.info("Cron executed Successfully for the Exchange Rate");
    } catch (final Exception exp) {
      log.error("Error while Updating the exchange symbols: {}", exp.getMessage());
    }
  }

  /**
   * Add or update exchange.
   *
   * @param exchangeDetails the exchange details
   */
  private void addOrUpdateExchange(final ExchangeDetails exchangeDetails) {
    exchangeDetailsDao.findBySymbolAndVendor(
        exchangeDetails.getSymbol(),
        exchangeDetails.getVendorId())
        .ifPresent(entity -> exchangeDetails.setId(entity.getId()));
    exchangeDetailsDao.save(exchangeDetails);
  }

  /**
   * Deactivate expired exchange.
   */
  private void deactivateExpiredExchange() {
    final var expiredExchange = exchangeDetailsDao
        .findByBeforeUpdatedAt(CommonUtils.getPastDate(2));
    log.info("Expired Exchange: {}", expiredExchange.size());
    expiredExchange.forEach(exchangeDetails -> {
      exchangeDetails.setStatus(RepoEnum.IN_ACTIVE);
      exchangeDetailsDao.save(exchangeDetails);
    });
  }
}
