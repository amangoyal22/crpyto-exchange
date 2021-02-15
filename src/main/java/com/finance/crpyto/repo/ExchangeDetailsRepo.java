package com.finance.crpyto.repo;

import com.finance.crpyto.enums.RepoEnum;
import com.finance.crpyto.model.repo.ExchangeDetails;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The interface Exchange details repo.
 */
public interface ExchangeDetailsRepo extends MongoRepository<ExchangeDetails, String> {

  /**
   * Find by symbol and vendor id and status optional.
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
   * Find by updated at before and status list.
   *
   * @param date     the date
   * @param repoEnum the repo enum
   * @return the list
   */
  List<ExchangeDetails> findByUpdatedAtBeforeAndStatus(final Date date,
                                                       final RepoEnum repoEnum);


  /**
   * Find all by status list.
   *
   * @param repoEnum the repo enum
   * @return the list
   */
  List<ExchangeDetails> findAllByStatus(final RepoEnum repoEnum);
}
