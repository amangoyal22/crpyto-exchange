package com.finance.crpyto.repo;

import com.finance.crpyto.enums.RepoEnum;
import com.finance.crpyto.model.repo.FiveMinutesDataDetails;
import java.util.Date;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The interface Five minutes data repo.
 */
public interface FiveMinutesDataRepo extends MongoRepository<FiveMinutesDataDetails, String> {

  /**
   * Find by timestamp between and status order by timestamp asc list.
   *
   * @param lowerLimit the lower limit
   * @param upperLimit the upper limit
   * @param repoEnum   the repo enum
   * @return the list
   */
  List<FiveMinutesDataDetails> findByTimestampBetweenAndStatusOrderByTimestampAsc(
      final Date lowerLimit, final Date upperLimit, final RepoEnum repoEnum);
}
