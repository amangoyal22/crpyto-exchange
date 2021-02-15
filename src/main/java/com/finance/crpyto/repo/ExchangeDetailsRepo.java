package com.finance.crpyto.repo;

import com.finance.crpyto.enums.RepoEnum;
import com.finance.crpyto.model.repo.ExchangeDetails;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Exchange details repo.
 */
@Repository
public interface ExchangeDetailsRepo extends MongoRepository<ExchangeDetails, String> {

  /**
   * Find first by symbol and vendor id and status optional.
   *
   * @param symbol   the symbol
   * @param vendorId the vendor id
   * @param repoEnum the repo enum
   * @return the optional
   */
  Optional<ExchangeDetails> findBySymbolAndVendorIdAndStatus(final String symbol,
                                                             final String vendorId,
                                                             final RepoEnum repoEnum);

  /**
   * Find by updated at before and status optional.
   *
   * @param date     the date
   * @param repoEnum the repo enum
   * @return the optional
   */
  List<ExchangeDetails> findByUpdatedAtBeforeAndStatus(final Date date,
                                                       final RepoEnum repoEnum);
}
