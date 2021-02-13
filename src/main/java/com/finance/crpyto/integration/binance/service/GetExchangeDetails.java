package com.finance.crpyto.integration.binance.service;

import com.finance.crpyto.enums.VendorEnum;
import com.finance.crpyto.integration.IExchange;
import com.finance.crpyto.integration.binance.mapper.ResponseMapper;
import com.finance.crpyto.integration.binance.model.ExchangeListResponse;
import com.finance.crpyto.integration.binance.properties.BiananceApiProperties;
import com.finance.crpyto.model.repo.ExchangeDetails;
import com.finance.crpyto.registries.ExchangeRegistry;
import com.finance.crpyto.utils.JsonUtils;
import com.finance.crpyto.utils.RestUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import org.asynchttpclient.AsyncHttpClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

/**
 * The type Get exchange details.
 */
@Service
@AllArgsConstructor
@Slf4j
public class GetExchangeDetails implements IExchange {

  /**
   * The Binance async http client.
   */
  @Qualifier("binance")
  private final AsyncHttpClient binanceAsyncHttpClient;
  /**
   * The Bianance api properties.
   */
  private final BiananceApiProperties biananceApiProperties;
  /**
   * The Rest utils.
   */
  private final RestUtils restUtils;
  /**
   * The Response mapper.
   */
  private final ResponseMapper responseMapper;

  /**
   * Gets exchange details.
   *
   * @return the exchange details
   */
  @Override
  public List<ExchangeDetails> getExchangeDetails() {
    final var exchangeDetails = new ArrayList<ExchangeDetails>();
    final var response = apiCall();
    if (Objects.nonNull(response)) {
      exchangeDetails.addAll(
          responseMapper.generateListOfExchangeDetails(
              response.getSymbols(),
              VendorEnum.BINANCE.getId()));
      log.info("{}", response.getSymbols().size());
    }
    return exchangeDetails;
  }

  /**
   * Register.
   */
  @Override
  @PostConstruct
  public void register() {
    ExchangeRegistry.register(VendorEnum.BINANCE.getId(), this);
  }

  /**
   * Api call exchange list response.
   *
   * @return the exchange list response
   */
  private ExchangeListResponse apiCall() {
    try {
      final var request = binanceAsyncHttpClient.prepareGet(getExchangeUrl())
          .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
          .build();
      log.info("Binance Exchange Info url: {}", request.getUrl());
      final var response = restUtils.executeWithRetry(request,
          binanceAsyncHttpClient);
      final String responseBody = response.getResponseBody();
      return JsonUtils.JsonToObject(responseBody, ExchangeListResponse.class);
    } catch (final Exception exp) {
      log.error("Error while calling binance {}", exp.getMessage());
      return null;
    }
  }

  /**
   * Gets exchange url.
   *
   * @return the exchange url
   */
  private String getExchangeUrl() {

    return new HttpUrl.Builder()
        .scheme(biananceApiProperties.getScheme())
        .host(biananceApiProperties.getHost())
        .addPathSegments(biananceApiProperties.getPathSegment())
        .addPathSegment(biananceApiProperties.getExchangeList().getPath())
        .build().toString();
  }
}
