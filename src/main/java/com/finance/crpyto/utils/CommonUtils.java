package com.finance.crpyto.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * The type Common utils.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CommonUtils {


  /**
   * The constant OBJECT_MAPPER.
   */
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
      .setSerializationInclusion(JsonInclude.Include.NON_NULL)
      .configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

  /**
   * Gets past date.
   *
   * @param days the days
   * @return the past date
   */
  public static Date getPastDate(final int days) {
    return Date.from(
        Instant.now().minus(
            days, ChronoUnit.DAYS));
  }

  /**
   * Gets object from string.
   *
   * @param <T>      the type parameter
   * @param convert  the convert
   * @param response the response
   * @return the object from string
   */
  public static <T> Object getObjectFromString(final Class<T> convert, final String response) {
    try {
      return OBJECT_MAPPER.readValue(response, convert);
    } catch (final Exception exp) {
      log.error("Error while conversion");
    }
    return null;
  }
}
