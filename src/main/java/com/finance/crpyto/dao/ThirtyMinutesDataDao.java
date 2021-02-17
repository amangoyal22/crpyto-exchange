package com.finance.crpyto.dao;

import com.finance.crpyto.enums.RepoEnum;
import com.finance.crpyto.model.repo.ThirtyMinutesDataDetails;
import com.finance.crpyto.repo.ThirtyMinutesDataRepo;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * The type Thirty minutes data dao.
 */
@Service
@AllArgsConstructor
public class ThirtyMinutesDataDao {

  /**
   * The Thirty minutes data repo.
   */
  private final ThirtyMinutesDataRepo thirtyMinutesDataRepo;


  /**
   * Save.
   *
   * @param thirtyMinutesDataDetails the thirty minutes data details
   */
  public void save(final ThirtyMinutesDataDetails thirtyMinutesDataDetails) {
    thirtyMinutesDataRepo.save(thirtyMinutesDataDetails);
  }

  /**
   * Find by time stamp list.
   *
   * @param upperLimit the upper limit
   * @param lowerLimit the lower limit
   * @return the list
   */
  public List<ThirtyMinutesDataDetails> findByTimeStamp(
      final Date upperLimit,
      final Date lowerLimit) {
    return thirtyMinutesDataRepo.findByTimestampBetweenAndStatusOrderByTimestampAsc(
        lowerLimit, upperLimit, RepoEnum.ACTIVE);
  }
}
