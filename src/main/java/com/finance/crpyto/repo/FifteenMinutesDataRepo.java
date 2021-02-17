package com.finance.crpyto.repo;

import com.finance.crpyto.model.repo.FifteenMinutesDataDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The interface Fifteen minutes data repo.
 */
public interface FifteenMinutesDataRepo extends MongoRepository<FifteenMinutesDataDetails, String> {
}
