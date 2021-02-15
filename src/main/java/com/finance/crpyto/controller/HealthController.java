package com.finance.crpyto.controller;

import com.finance.crpyto.service.CandleStickCronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Health controller.
 */
@RestController
public class HealthController {

  /**
   * The App version.
   */
  @Value("${spring.application.name}")
  private String appVersion;

  @Autowired
  private CandleStickCronService candleStickCronService;

  /**
   * Gets app version.
   *
   * @return the app version
   */
  @GetMapping(path = {"/appVersion", "/status"}, produces = {MediaType.TEXT_PLAIN_VALUE})
  public ResponseEntity<String> getAppVersion() {
    candleStickCronService.executeExchangeCron();
    return new ResponseEntity<>(appVersion, HttpStatus.OK);
  }
}
