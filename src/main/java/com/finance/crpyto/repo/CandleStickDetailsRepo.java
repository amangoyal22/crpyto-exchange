package com.finance.crpyto.repo;

import com.finance.crpyto.enums.RepoEnum;
import com.finance.crpyto.model.repo.CandleStickDetails;
import java.util.Date;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The interface Candle stick details repo.
 */
public interface CandleStickDetailsRepo extends MongoRepository<CandleStickDetails, String> {

  /**
   * Find by timestamp between and status order by timestamp asc list.
   *
   * @param timeStart the time start
   * @param timeEnd   the time end
   * @param repoEnum  the repo enum
   * @return the list
   */
  List<CandleStickDetails> findByTimestampBetweenAndStatusOrderByTimestampAsc(final Date timeStart,
                                                                              final Date timeEnd,
                                                                              final RepoEnum repoEnum);
}
