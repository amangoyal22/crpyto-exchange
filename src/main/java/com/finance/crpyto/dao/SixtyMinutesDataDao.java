package com.finance.crpyto.dao;

import com.finance.crpyto.model.repo.SixtyMinutesDataDetails;
import com.finance.crpyto.repo.SixtyMinutesDataRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * The type Sixty minutes data dao.
 */
@Service
@AllArgsConstructor
public class SixtyMinutesDataDao {

  /**
   * The Sixty minutes data dao.
   */
  private final SixtyMinutesDataRepo sixtyMinutesDataRepo;


  /**
   * Save.
   *
   * @param sixtyMinutesDataDetails the sixty minutes data details
   */
  public void save(final SixtyMinutesDataDetails sixtyMinutesDataDetails) {
    sixtyMinutesDataRepo.save(sixtyMinutesDataDetails);
  }
}
