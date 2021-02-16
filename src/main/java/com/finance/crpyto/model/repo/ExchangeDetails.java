package com.finance.crpyto.model.repo;

import com.finance.crpyto.enums.RepoEnum;
import java.time.Instant;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Repository;

/**
 * The type Exchange details.
 */
@Repository
@Builder
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
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
   * The Base commission precision.
   */
  @Field("base_commission_precision")
  private int baseCommissionPrecision;

  /**
   * The Quote commission precision.
   */
  @Field("quote_commission_precision")
  private int quoteCommissionPrecision;

  /**
   * The Status.
   */
  @Field("status")
  @Builder.Default
  private RepoEnum status = RepoEnum.ACTIVE;

  /**
   * The Updated at.
   */
  @Field("updated_at")
  private Date updatedAt;
}
