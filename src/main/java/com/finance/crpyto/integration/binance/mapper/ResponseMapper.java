package com.finance.crpyto.integration.binance.mapper;

import com.finance.crpyto.enums.RepoEnum;
import com.finance.crpyto.integration.binance.model.SymbolDetail;
import com.finance.crpyto.model.repo.ExchangeDetails;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

/**
 * The interface Response mapper.
 */
@Mapper(componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface ResponseMapper {

  /**
   * Generate list of exchange details list.
   *
   * @param symbolDetails the symbol details
   * @param vendorId      the vendor id
   * @return the list
   */
  default List<ExchangeDetails> generateListOfExchangeDetails(
      final List<SymbolDetail> symbolDetails,
      final String vendorId) {
    return symbolDetails.stream()
        .map(symbolDetail -> generateExchangeDetails(symbolDetail,vendorId))
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
  }

  /**
   * Generate exchange details exchange details.
   *
   * @param symbolDetail the symbol detail
   * @param vendorId     the vendor id
   * @return the exchange details
   */
  default ExchangeDetails generateExchangeDetails(
      final SymbolDetail symbolDetail,
      final String vendorId) {
    if (Objects.nonNull(symbolDetail)) {
      return ExchangeDetails
          .builder()
          .vendorId(vendorId)
          .symbol(symbolDetail.getSymbol())
          .baseAssest(symbolDetail.getBaseAsset())
          .quoteAssest(symbolDetail.getQuoteAsset())
          .baseAssestPrecision(symbolDetail.getBaseAssetPrecision())
          .quoteAssestPrecision(symbolDetail.getQuotePrecision())
          .quoteCommissionPrecision(symbolDetail.getQuoteCommissionPrecision())
          .baseCommissionPrecision(symbolDetail.getBaseCommissionPrecision())
          .status(RepoEnum.ACTIVE)
          .build();
    }
    return null;
  }
}
