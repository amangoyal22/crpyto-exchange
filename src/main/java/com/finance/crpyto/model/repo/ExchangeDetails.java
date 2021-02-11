package com.finance.crpyto.model.repo;

import com.finance.crpyto.enums.RepoEnum;
import lombok.Builder;
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
   * The Status.
   */
  @Field("status")
  @Builder.Default
  private RepoEnum status = RepoEnum.ACTIVE;
}
