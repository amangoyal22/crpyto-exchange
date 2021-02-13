package com.finance.crpyto.controller;

import com.finance.crpyto.enums.VendorEnum;
import com.finance.crpyto.registries.ExchangeRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Main controller.
 */
@RestController
public class MainController {

  /**
   * Gets exchange list.
   *
   * @return the exchange list
   */
  @GetMapping(path = {"/exchangeList"}, produces = {MediaType.TEXT_PLAIN_VALUE})
  public ResponseEntity<String> getExchangeList() {
    ExchangeRegistry.get(VendorEnum.BINANCE.getId()).getExchangeDetails();
    return new ResponseEntity<>("", HttpStatus.OK);
  }
}
