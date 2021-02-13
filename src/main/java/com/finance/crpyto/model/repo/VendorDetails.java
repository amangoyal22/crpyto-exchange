package com.finance.crpyto.model.repo;

import com.finance.crpyto.enums.RepoEnum;
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
 * The type Vendor details.
 */
@Repository
@Builder
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Document("vendor_details")
public class VendorDetails {

  /**
   * The Id.
   */
  @Id
  private String id;

  /**
   * The Name.
   */
  @Field("name")
  private String name;

  /**
   * The Status.
   */
  @Field("status")
  @Builder.Default
  private int status = RepoEnum.ACTIVE.ordinal();
}
