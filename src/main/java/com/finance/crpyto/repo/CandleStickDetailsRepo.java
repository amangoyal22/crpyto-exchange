package com.finance.crpyto.repo;

import com.finance.crpyto.model.repo.CandleStickDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The interface Candle stick details repo.
 */
public interface CandleStickDetailsRepo extends MongoRepository<CandleStickDetails, String> {

}
