package com.finance.crpyto.repo;

import com.finance.crpyto.model.repo.ExchangeDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The type Exchange details repo.
 */
public interface ExchangeDetailsRepo extends MongoRepository<ExchangeDetails, String> {
}
