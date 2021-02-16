package com.finance.crpyto.dao;

import com.finance.crpyto.model.repo.CandleStickDetails;
import com.finance.crpyto.repo.CandleStickDetailsRepo;
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
   * Save candle stick details.
   *
   * @param candleStickDetails the candle stick details
   * @return the candle stick details
   */
  public CandleStickDetails save(final CandleStickDetails candleStickDetails) {
    return candleStickDetailsRepo.save(candleStickDetails);
  }

  /**
   * Delete all.
   */
  public void deleteAll() {
    candleStickDetailsRepo.deleteAll();
  }
}
