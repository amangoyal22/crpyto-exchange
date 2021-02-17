package com.finance.crpyto.dao;

import com.finance.crpyto.model.repo.FiveMinutesDataDetails;
import com.finance.crpyto.model.repo.ThirtyMinutesDataDetails;
import com.finance.crpyto.repo.FiveMinutesDataRepo;
import com.finance.crpyto.repo.ThirtyMinutesDataRepo;
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
}
