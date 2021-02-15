package com.finance.crpyto.integration.binance.mapper;

import com.finance.crpyto.enums.RepoEnum;
import com.finance.crpyto.enums.VendorEnum;
import com.finance.crpyto.integration.binance.constant.ParameterConstantUtils;
import com.finance.crpyto.integration.binance.model.KlinesDetails;
import com.finance.crpyto.integration.binance.model.SymbolDetail;
import com.finance.crpyto.model.repo.CandleStickDetails;
import com.finance.crpyto.model.repo.ExchangeDetails;
import java.util.Date;
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
        .map(symbolDetail -> generateExchangeDetails(symbolDetail, vendorId))
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
  }

  /**
   * Generate candle stick details candle stick details.
   *
   * @param klinesDetails the klines details
   * @param symbol        the symbol
   * @param vendorEnum    the vendor enum
   * @return the candle stick details
   */
  default CandleStickDetails generateCandleStickDetails(
      final KlinesDetails klinesDetails,
      final String symbol,
      final VendorEnum vendorEnum) {

    final var candleStickDetailsBuilder = CandleStickDetails.builder();

    if (Objects.nonNull(klinesDetails)) {
      candleStickDetailsBuilder
          .close(klinesDetails.getClose())
          .closeTime(new Date(klinesDetails.getCloseTime()))
          .openTime(new Date(klinesDetails.getOpenTime()))
          .open(klinesDetails.getOpen())
          .high(klinesDetails.getHigh())
          .low(klinesDetails.getLow())
          .volume(klinesDetails.getVolume())
          .numberOfTrades(klinesDetails.getNumberOfTrades())
          .takerBuyBaseAssestVolume(klinesDetails.getTakerBuyBaseAssestVolume())
          .takerBuyQuoteAssestVolume(klinesDetails.getTakerBuyQuoteAssestVolume())
          .quoteAssestVolume(klinesDetails.getQuoteAssestVolume());
    }
    return candleStickDetailsBuilder
        .symbol(symbol)
        .vendorId(vendorEnum.getId())
        .timestamp(new Date())
        .status(RepoEnum.ACTIVE)
        .build();
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

  /**
   * Gets klines details 1 m.
   *
   * @param resposne the resposne
   * @return the klines details 1 m
   */
  default KlinesDetails getKlinesDetails1m(final List resposne) {
    if (!resposne.isEmpty()) {
      final var details = (List<Object>) resposne.get(0);
      return KlinesDetails
          .builder()
          .openTime(
              Long.parseLong(
                  String.valueOf(details.get(ParameterConstantUtils.Klines.OPEN_TIME))))
          .closeTime(
              Long.parseLong(
                  String.valueOf(details.get(ParameterConstantUtils.Klines.CLOSE_TIME))))
          .open(
              Double.parseDouble(
                  String.valueOf(
                      details.get(ParameterConstantUtils.Klines.CLOSE_TIME))))
          .close(
              Double.parseDouble(
                  String.valueOf(details.get(ParameterConstantUtils.Klines.CLOSE))))
          .low(
              Double.parseDouble(
                  String.valueOf(details.get(ParameterConstantUtils.Klines.LOW))))
          .high(
              Double.parseDouble(
                  String.valueOf(details.get(ParameterConstantUtils.Klines.HIGH))))
          .volume(
              Double.parseDouble(
                  String.valueOf(details.get(ParameterConstantUtils.Klines.VOLUME))))
          .numberOfTrades(
              Integer.parseInt(
                  String.valueOf(details.get(ParameterConstantUtils.Klines.NO_OF_TRADES))))
          .takerBuyBaseAssestVolume(
              Double.parseDouble(
                  String.valueOf(details.get(ParameterConstantUtils.Klines.BUY_BASE_ASSEST_VOLUME))))
          .takerBuyQuoteAssestVolume(
              Double.parseDouble(
                  String.valueOf(details.get(ParameterConstantUtils.Klines.BUY_QUOTE_ASSEST_VOLUME))))
          .quoteAssestVolume(
              Double.parseDouble(
                  String.valueOf(details.get(ParameterConstantUtils.Klines.QUOTE_VOL))))
          .build();
    }
    return null;
  }
}
