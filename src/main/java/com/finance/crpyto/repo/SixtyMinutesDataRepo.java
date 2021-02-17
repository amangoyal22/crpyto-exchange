package com.finance.crpyto.repo;

import com.finance.crpyto.model.repo.SixtyMinutesDataDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The interface Sixty minutes data repo.
 */
public interface SixtyMinutesDataRepo extends MongoRepository<SixtyMinutesDataDetails, String> {
}
