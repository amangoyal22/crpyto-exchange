package com.finance.crpyto.integration.binance.service;

import com.finance.crpyto.dao.CandleSticksDetailsDao;
import com.finance.crpyto.enums.VendorEnum;
import com.finance.crpyto.integration.ICandleSticks;
import com.finance.crpyto.integration.binance.constant.ParameterConstantUtils;
import com.finance.crpyto.integration.binance.mapper.ResponseMapper;
import com.finance.crpyto.integration.binance.model.KlinesDetails;
import com.finance.crpyto.integration.binance.properties.BiananceApiProperties;
import com.finance.crpyto.registries.CandleSticksRegistry;
import com.finance.crpyto.utils.CommonUtils;
import com.finance.crpyto.utils.RestUtils;
import java.util.List;
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
 * The type Get candle sticks.
 */
@Service
@AllArgsConstructor
@Slf4j
public class GetCandleSticks implements ICandleSticks {

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
   * Gets candle stick for 1 m.
   *
   * @param time   the time
   * @param symbol the symbol
   * @return the candle stick for 1 m
   */
  @Override
  public void getCandleStickFor1m(final long time,
                                  final String symbol,
                                  final CandleSticksDetailsDao candleSticksDetailsDao) {
    final var response = apiCall(symbol, time);
    candleSticksDetailsDao.save(
        responseMapper.generateCandleStickDetails(
            response, symbol, VendorEnum.BINANCE, time));
  }

  /**
   * Register.
   */
  @Override
  @PostConstruct
  public void register() {
    CandleSticksRegistry.register(VendorEnum.BINANCE.getId(), this);
  }

  /**
   * Api call klines details.
   *
   * @param symbol the symbol
   * @return the klines details
   */
  private KlinesDetails apiCall(final String symbol,
                                final long startTime) {
    try {
      final var request = binanceAsyncHttpClient.prepareGet(getKlinesUrl(symbol, startTime))
          .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
          .build();
      final var response = restUtils.execute(request, binanceAsyncHttpClient);
      return responseMapper.getKlinesDetails1m(
          (List) CommonUtils.getObjectFromString(List.class, response.getResponseBody()));
    } catch (final Exception exp) {
      log.error("Error while calling Kline {} For Time {}",
          exp.getMessage(),
          CommonUtils.getTimeFromMiliToDateInUTC(startTime));
      return null;
    }
  }

  /**
   * Gets klines url.
   *
   * @param symbol the symbol
   * @return the klines url
   */
  private String getKlinesUrl(final String symbol,
                              final long startTime) {

    return new HttpUrl.Builder()
        .scheme(biananceApiProperties.getScheme())
        .host(biananceApiProperties.getHost())
        .addPathSegments(biananceApiProperties.getPathSegment())
        .addPathSegment(biananceApiProperties.getKlines().getPath())
        .addQueryParameter(ParameterConstantUtils.SYMBOL, symbol)
        .addQueryParameter(ParameterConstantUtils.START_TIME, String.valueOf(startTime))
        .addQueryParameter(
            ParameterConstantUtils.INTERVAL, ParameterConstantUtils.INTERVAL_VALUE_1M)
        .addQueryParameter(ParameterConstantUtils.LIMIT, ParameterConstantUtils.LIMIT_VALUE)
        .build().toString();
  }
}
