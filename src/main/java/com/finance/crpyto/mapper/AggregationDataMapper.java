package com.finance.crpyto.mapper;

import com.finance.crpyto.enums.RepoEnum;
import com.finance.crpyto.model.repo.CandleStickDetails;
import com.finance.crpyto.model.repo.FiveMinutesDataDetails;
import com.finance.crpyto.utils.CommonUtils;
import java.util.List;
import java.util.Objects;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

/**
 * The interface Aggregation data mapper.
 */
@Mapper(componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface AggregationDataMapper {

  /**
   * Gets five minutes data.
   *
   * @param symbol             the symbol
   * @param candleStickDetails the candle stick details
   * @param timeStamp          the time stamp
   * @return the five minutes data
   */
  default FiveMinutesDataDetails getFiveMinutesData(final String symbol,
                                                    final List<CandleStickDetails> candleStickDetails,
                                                    final long timeStamp) {
    final var firstStick = candleStickDetails.get(0);
    final var lastStick = candleStickDetails.get(candleStickDetails.size() - 1);
    final var data = FiveMinutesDataDetails.builder()
        .status(RepoEnum.ACTIVE)
        .symbol(symbol).build();
    candleStickDetails.forEach(candleStickDetail -> {
      data.setVendorId(candleStickDetail.getVendorId());
      data.setNumberOfTrades(data.getNumberOfTrades() + candleStickDetail.getNumberOfTrades());
      data.setHigh(data.getNumberOfTrades() + candleStickDetail.getNumberOfTrades());
      data.setLow(Math.min(data.getLow(), candleStickDetail.getLow()));
      data.setHigh(Math.max(data.getHigh(), candleStickDetail.getHigh()));
      data.setVolume(
          data.getVolume() + candleStickDetail.getVolume());
      data.setQuoteAssestVolume(
          data.getQuoteAssestVolume() + candleStickDetail.getQuoteAssestVolume());
      data.setTakerBuyBaseAssestVolume(
          data.getTakerBuyBaseAssestVolume() + candleStickDetail.getTakerBuyBaseAssestVolume());
      data.setTakerBuyQuoteAssestVolume(
          data.getTakerBuyQuoteAssestVolume() + candleStickDetail.getTakerBuyQuoteAssestVolume());
    });

    if (Objects.nonNull(lastStick)) {
      data.setClose(lastStick.getClose());
      data.setCloseTime(lastStick.getCloseTime());
    }
    if (Objects.nonNull(firstStick)) {
      data.setOpen(firstStick.getOpen());
      data.setOpenTime(firstStick.getOpenTime());
    }
    data.setTimestamp(CommonUtils.getTimeFromMiliToDateInUTC(timeStamp));
    return data;
  }
}
