package com.finance.crpyto.controller;

import com.finance.crpyto.constant.ConfigConstantUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * The type Health check cron controller.
 */
@RestController
public class HealthController {

  /**
   * The App version.
   */
  @Value("${spring.application.name}")
  private String appVersion;

  /**
   * Gets app version.
   *
   * @return the app version
   */
  @GetMapping(path = {"/appVersion", "/status"}, produces = {MediaType.TEXT_PLAIN_VALUE})
  public ModelAndView getAppVersion()
  {
    final var modelAndView = new ModelAndView();
    modelAndView.setViewName(ConfigConstantUtils.WEB_PAGE);
    return modelAndView;
  }

}
