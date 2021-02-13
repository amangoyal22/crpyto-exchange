package com.finance.crpyto.integration.models;

import java.io.Serializable;
import lombok.Data;

/**
 * The type Sub property model.
 */
@Data
public class SubPropertyModel implements Serializable {
  /**
   * The Path.
   */
  private String path;
  /**
   * The Retry duration.
   */
  private String retryDuration;
  /**
   * The Retry count.
   */
  private int retryCount;
}
