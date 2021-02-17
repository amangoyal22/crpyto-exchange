package com.finance.crpyto.repo;

import com.finance.crpyto.model.repo.FiveMinutesDataDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The interface Data aggregation five min details repo.
 */
public interface FiveMinutesDataRepo extends MongoRepository<FiveMinutesDataDetails, String> {
}
