package com.finance.crpyto.dao;

import com.finance.crpyto.enums.RepoEnum;
import com.finance.crpyto.model.repo.FiveMinutesDataDetails;
import com.finance.crpyto.repo.FiveMinutesDataRepo;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * The type Five minutes data dao.
 */
@Service
@AllArgsConstructor
public class FiveMinutesDataDao {

  /**
   * The Five minutes data repo.
   */
  private final FiveMinutesDataRepo fiveMinutesDataRepo;


  /**
   * Save.
   *
   * @param fiveMinutesDataDetails the five minutes data details
   */
  public void save(final FiveMinutesDataDetails fiveMinutesDataDetails) {
    fiveMinutesDataRepo.save(fiveMinutesDataDetails);
  }

  /**
   * Find by time stamp list.
   *
   * @param upperLimit the upper limit
   * @param lowerLimit the lower limit
   * @return the list
   */
  public List<FiveMinutesDataDetails> findByTimeStamp(final Date upperLimit,
                                                      final Date lowerLimit) {
    return fiveMinutesDataRepo.findByTimestampBetweenAndStatusOrderByTimestampAsc(
        lowerLimit, upperLimit, RepoEnum.ACTIVE);
  }
}
