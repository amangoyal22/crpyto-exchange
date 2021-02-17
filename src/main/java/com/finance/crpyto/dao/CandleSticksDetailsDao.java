package com.finance.crpyto.dao;

import com.finance.crpyto.enums.RepoEnum;
import com.finance.crpyto.model.repo.CandleStickDetails;
import com.finance.crpyto.repo.CandleStickDetailsRepo;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * The type Candle sticks details dao.
 */
@Service
@AllArgsConstructor
public class CandleSticksDetailsDao {

  /**
   * The Candle stick details repo.
   */
  private final CandleStickDetailsRepo candleStickDetailsRepo;


  /**
   * Save.
   *
   * @param candleStickDetails the candle stick details
   */
  public void save(final CandleStickDetails candleStickDetails) {
    candleStickDetailsRepo.save(candleStickDetails);
  }

  /**
   * Delete all.
   */
  public void deleteAll() {
    candleStickDetailsRepo.deleteAll();
  }

  /**
   * Find by time stamp list.
   *
   * @param upperLimit the upper limit
   * @param lowerLimit the lower limit
   * @return the list
   */
  public List<CandleStickDetails> findByTimeStamp(final Date upperLimit,
                                                  final Date lowerLimit) {
    return candleStickDetailsRepo.findByTimestampBetweenAndStatusOrderByTimestampAsc(
        lowerLimit, upperLimit, RepoEnum.ACTIVE);
  }
}
