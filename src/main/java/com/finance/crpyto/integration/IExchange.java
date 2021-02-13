package com.finance.crpyto.integration;

import com.finance.crpyto.model.repo.ExchangeDetails;
import java.util.List;

/**
 * The interface Exchange.
 */
public interface IExchange {

  /**
   * Gets exchange details.
   *
   * @return the exchange details
   */
  List<ExchangeDetails> getExchangeDetails();

  /**
   * Register.
   */
  void register();
}
