package com.finance.crpyto.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * The type Json utils.
 */
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class JsonUtils {

  /**
   * To json string.
   *
   * @param <T> the type parameter
   * @param obj the obj
   * @return the string
   * @throws JsonProcessingException the json processing exception
   */
  public static <T extends Serializable> String toJson(T obj) throws JsonProcessingException {
    return (new ObjectMapper()).writeValueAsString(obj);
  }

  /**
   * Json to object t.
   *
   * @param <T>   the type parameter
   * @param json  the json
   * @param clazz the clazz
   * @return the t
   * @throws IOException the io exception
   */
  public static <T extends Serializable> T JsonToObject(String json, Class<T> clazz) throws IOException {
    return (T) (new ObjectMapper()).readValue(json, clazz);
  }
  /**
   * To json string.
   *
   * @param obj the obj
   * @return the string
   * @throws JsonProcessingException the json processing exception
   */
  public static String toJson(Object obj) throws JsonProcessingException {
    return (new ObjectMapper()).writeValueAsString(obj);
  }
}
