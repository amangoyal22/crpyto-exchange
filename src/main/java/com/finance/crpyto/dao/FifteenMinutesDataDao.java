package com.finance.crpyto.dao;

import com.finance.crpyto.enums.RepoEnum;
import com.finance.crpyto.model.repo.FifteenMinutesDataDetails;
import com.finance.crpyto.repo.FifteenMinutesDataRepo;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * The type Fifteen minutes data dao.
 */
@Service
@AllArgsConstructor
public class FifteenMinutesDataDao {

  /**
   * The Fifteen minutes data dao.
   */
  private final FifteenMinutesDataRepo fifteenMinutesDataRepo;


  /**
   * Save.
   *
   * @param fifteenMinutesDataDetails the fifteen minutes data details
   */
  public void save(final FifteenMinutesDataDetails fifteenMinutesDataDetails) {
    fifteenMinutesDataRepo.save(fifteenMinutesDataDetails);
  }

  /**
   * Find by time stamp list.
   *
   * @param upperLimit the upper limit
   * @param lowerLimit the lower limit
   * @return the list
   */
  public List<FifteenMinutesDataDetails> findByTimeStamp(
      final Date upperLimit,
      final Date lowerLimit) {
    return fifteenMinutesDataRepo.findByTimestampBetweenAndStatusOrderByTimestampAsc(
        lowerLimit, upperLimit, RepoEnum.ACTIVE);
  }
}
