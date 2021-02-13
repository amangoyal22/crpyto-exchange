package com.finance.crpyto.dao;

import com.finance.crpyto.model.repo.VendorDetails;
import com.finance.crpyto.repo.VendorDetailsRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * The type Vendor details dao.
 */
@Service
@AllArgsConstructor
public class VendorDetailsDao {

  /**
   * The Vendor details repo.
   */
  private final VendorDetailsRepo vendorDetailsRepo;

  /**
   * Save vendor details.
   *
   * @param vendorDetails the vendor details
   * @return the vendor details
   */
  public VendorDetails save(final VendorDetails vendorDetails) {
    return vendorDetailsRepo.save(vendorDetails);
  }
}
