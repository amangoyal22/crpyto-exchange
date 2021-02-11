package com.finance.crpyto.config;

import com.finance.crpyto.constant.ConfigConstantUtils;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type In memory cache config.
 */
@Data
@EnableCaching
@Configuration
@ConfigurationProperties(prefix = "in-memory-cache")
public class InMemoryCacheConfig {
  /**
   * The Ttl.
   */
  private String ttl;
  /**
   * The Size.
   */
  private int size;

  /**
   * Db cache caffeine cache.
   *
   * @return the caffeine cache
   */
  @Bean(name = "dbCache")
  public CaffeineCache dbCache() {
    return new CaffeineCache(ConfigConstantUtils.DB_DETAILS,
        Caffeine.newBuilder()
            .expireAfterWrite(
                Duration.parse(ttl).getSeconds(), TimeUnit.SECONDS)
            .maximumSize(size)
            .build(), false);
  }
}
