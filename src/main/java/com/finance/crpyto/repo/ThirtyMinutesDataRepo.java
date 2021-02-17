package com.finance.crpyto.repo;

import com.finance.crpyto.enums.RepoEnum;
import com.finance.crpyto.model.repo.ThirtyMinutesDataDetails;
import java.util.Date;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The interface Thirty minutes data repo.
 */
public interface ThirtyMinutesDataRepo extends MongoRepository<ThirtyMinutesDataDetails, String> {

  /**
   * Find by timestamp between and status order by timestamp asc list.
   *
   * @param lowerLimit the lower limit
   * @param upperLimit the upper limit
   * @param repoEnum   the repo enum
   * @return the list
   */
  List<ThirtyMinutesDataDetails> findByTimestampBetweenAndStatusOrderByTimestampAsc(
      final Date lowerLimit, final Date upperLimit, final RepoEnum repoEnum);
}
