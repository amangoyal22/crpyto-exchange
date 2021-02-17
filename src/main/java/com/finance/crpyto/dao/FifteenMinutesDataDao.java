package com.finance.crpyto.dao;

import com.finance.crpyto.model.repo.FifteenMinutesDataDetails;
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
  private final FifteenMinutesDataDao fifteenMinutesDataDao;


  /**
   * Save.
   *
   * @param fifteenMinutesDataDetails the fifteen minutes data details
   */
  public void save(final FifteenMinutesDataDetails fifteenMinutesDataDetails) {
    fifteenMinutesDataDao.save(fifteenMinutesDataDetails);
  }
}
