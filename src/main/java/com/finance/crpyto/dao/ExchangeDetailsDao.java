package com.finance.crpyto.dao;

import com.finance.crpyto.enums.RepoEnum;
import com.finance.crpyto.model.repo.ExchangeDetails;
import com.finance.crpyto.repo.ExchangeDetailsRepo;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * The type Exchange details dao.
 */
@Service
@AllArgsConstructor
public class ExchangeDetailsDao {

  /**
   * The Exchange details repo.
   */
  private final ExchangeDetailsRepo exchangeDetailsRepo;


  /**
   * Find by symbol and vendor optional.
   *
   * @param symbol   the symbol
   * @param vendorId the vendor id
   * @return the optional
   */
  public Optional<ExchangeDetails> findBySymbolAndVendor(final String symbol, final String vendorId) {
    return exchangeDetailsRepo
        .findBySymbolAndVendorIdAndStatus(symbol, vendorId, RepoEnum.ACTIVE);
  }

  /**
   * Save.
   *
   * @param exchangeDetails the exchange details
   */
  public void save(final ExchangeDetails exchangeDetails) {
    exchangeDetailsRepo.save(exchangeDetails);
  }

  /**
   * Find by before updated at list.
   *
   * @param date the date
   * @return the list
   */
  public List<ExchangeDetails> findByBeforeUpdatedAt(final Date date) {
    return exchangeDetailsRepo.findByUpdatedAtBeforeAndStatus(date, RepoEnum.ACTIVE);
  }
}
