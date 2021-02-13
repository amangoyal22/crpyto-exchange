package com.finance.crpyto.repo;

import com.finance.crpyto.model.repo.VendorDetails;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The interface Vendor details repo.
 */
public interface VendorDetailsRepo extends MongoRepository<VendorDetails, String> {

  /**
   * Find all by status list.
   *
   * @param status the status
   * @return the list
   */
  List<VendorDetails> findAllByStatus(final int status);
}
