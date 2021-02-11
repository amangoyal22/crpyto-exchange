package com.finance.crpyto.config;

import com.finance.crpyto.model.config.RestClientConfigModel;
import java.time.Duration;
import lombok.Setter;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.Dsl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Rest client config.
 */
@Setter
@Configuration
@ConfigurationProperties(prefix = "rest-client")
public class RestClientConfig {

  /**
   * The Binance.
   */
  private RestClientConfigModel binance;

  /**
   * Binance async http client async http client.
   *
   * @return the async http client
   */
  @Bean("binance")
  public AsyncHttpClient binanceAsyncHttpClient() {
    final DefaultAsyncHttpClientConfig.Builder clientBuilder = Dsl.config()
        .setConnectTimeout(Integer
            .parseInt(String.valueOf(Duration.parse(binance.getConnectionTimeOut()).toMillis())))
        .setReadTimeout(Integer
            .parseInt(String.valueOf(Duration.parse(binance.getReadTimeOut()).toMillis())));
    return Dsl.asyncHttpClient(clientBuilder);
  }

}
