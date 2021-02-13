package com.finance.crpyto.repo;

import com.finance.crpyto.model.repo.VendorDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The type Exchange details repo.
 */
public interface VendorDetailsRepo extends MongoRepository<VendorDetails, String> {
}
