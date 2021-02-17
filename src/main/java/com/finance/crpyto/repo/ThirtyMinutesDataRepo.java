package com.finance.crpyto.repo;

import com.finance.crpyto.model.repo.ThirtyMinutesDataDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The interface Thirty minutes data repo.
 */
public interface ThirtyMinutesDataRepo extends MongoRepository<ThirtyMinutesDataDetails, String> {
}
