package com.finance.crpyto.integration.binance.service;

import com.finance.crpyto.enums.VendorEnum;
import com.finance.crpyto.integration.IExchange;
import com.finance.crpyto.model.repo.ExchangeDetails;
import com.finance.crpyto.registries.ExchangeRegistry;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * The type Get exchange details.
 */
@Service
public class GetExchangeDetails implements IExchange {

  /**
   * Gets exchange details.
   *
   * @return the exchange details
   */
  @Override
  public List<ExchangeDetails> getExchangeDetails() {
    return null;
  }

  /**
   * Register.
   */
  @Override
  public void register() {
    ExchangeRegistry.register(VendorEnum.BINANCE.getId(), this);
  }
}
