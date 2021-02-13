package com.finance.crpyto.model.repo;

import com.finance.crpyto.enums.RepoEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Repository;

/**
 * The type Exchange details.
 */
@Repository
@Document("exchange_details")
public class ExchangeDetails {

  /**
   * The Id.
   */
  @Id
  private String id;
  /**
   * The Vendor id.
   */
  @Field("vendor_id")
  private String vendorId;
  /**
   * The Symbol.
   */
  @Field("symbol")
  private String symbol;
  /**
   * The Base assest.
   */
  @Field("base_assest")
  private String baseAssest;
  /**
   * The Base assest precision.
   */
  @Field("base_assest_precision")
  private int baseAssestPrecision;
  /**
   * The Quote assest.
   */
  @Field("quote_assest")
  private String quoteAssest;
  /**
   * The Quote assest precision.
   */
  @Field("quote_assest_precision")
  private int quoteAssestPrecision;
  /**
   * The Status.
   */
  @Field("status")
  private RepoEnum status = RepoEnum.ACTIVE;
}
