package com.finance.crpyto.mapper;

import com.finance.crpyto.constant.ConfigConstantUtils;
import com.finance.crpyto.enums.RepoEnum;
import com.finance.crpyto.model.repo.CandleStickDetails;
import com.finance.crpyto.model.repo.FifteenMinutesDataDetails;
import com.finance.crpyto.model.repo.FiveMinutesDataDetails;
import com.finance.crpyto.model.repo.SixtyMinutesDataDetails;
import com.finance.crpyto.model.repo.ThirtyMinutesDataDetails;
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

      if (data.getVolume() == ConfigConstantUtils.DEFAULT_DOUBLE) {
        data.setFailureRate(data.getFailureRate() + 1);
      }
    });

    if (Objects.nonNull(lastStick)) {
      data.setClose(lastStick.getClose());
      data.setCloseTime(lastStick.getCloseTime());
    }
    if (Objects.nonNull(firstStick)) {
      data.setOpen(firstStick.getOpen());
      data.setOpenTime(firstStick.getOpenTime());
    }
    data.setFailureRate(data.getFailureRate() / candleStickDetails.size());
    data.setTimestamp(CommonUtils.getTimeFromMiliToDateInUTC(timeStamp));
    return data;
  }

  /**
   * Gets fifteen minutes data.
   *
   * @param symbol                 the symbol
   * @param fiveMinutesDataDetails the five minutes data details
   * @param timeStamp              the time stamp
   * @return the fifteen minutes data
   */
  default FifteenMinutesDataDetails getFifteenMinutesData(
      final String symbol,
      final List<FiveMinutesDataDetails> fiveMinutesDataDetails,
      final long timeStamp) {

    final var firstStick = fiveMinutesDataDetails.get(0);
    final var lastStick = fiveMinutesDataDetails.get(fiveMinutesDataDetails.size() - 1);
    final var data = FifteenMinutesDataDetails.builder()
        .status(RepoEnum.ACTIVE)
        .symbol(symbol)
        .build();

    fiveMinutesDataDetails
        .forEach(
            fiveMinutesDataDetail -> {
              data.setVendorId(fiveMinutesDataDetail.getVendorId());
              data.setNumberOfTrades(data.getNumberOfTrades() + fiveMinutesDataDetail.getNumberOfTrades());
              data.setLow(Math.min(data.getLow(), fiveMinutesDataDetail.getLow()));
              data.setHigh(Math.max(data.getHigh(), fiveMinutesDataDetail.getHigh()));
              data.setVolume(
                  data.getVolume() + fiveMinutesDataDetail.getVolume());
              data.setQuoteAssestVolume(
                  data.getQuoteAssestVolume() + fiveMinutesDataDetail.getQuoteAssestVolume());
              data.setTakerBuyBaseAssestVolume(
                  data.getTakerBuyBaseAssestVolume() + fiveMinutesDataDetail.getTakerBuyBaseAssestVolume());
              data.setTakerBuyQuoteAssestVolume(
                  data.getTakerBuyQuoteAssestVolume() + fiveMinutesDataDetail.getTakerBuyQuoteAssestVolume());

              if (data.getVolume() == ConfigConstantUtils.DEFAULT_DOUBLE) {
                data.setFailureRate(data.getFailureRate() + 1);
              }
            });

    if (Objects.nonNull(lastStick)) {
      data.setClose(lastStick.getClose());
      data.setCloseTime(lastStick.getCloseTime());
    }
    if (Objects.nonNull(firstStick)) {
      data.setOpen(firstStick.getOpen());
      data.setOpenTime(firstStick.getOpenTime());
    }
    data.setFailureRate(data.getFailureRate() / fiveMinutesDataDetails.size());
    data.setTimestamp(CommonUtils.getTimeFromMiliToDateInUTC(timeStamp));
    return data;
  }

  /**
   * Gets thirty minutes data.
   *
   * @param symbol                    the symbol
   * @param fifteenMinutesDataDetails the fifteen minutes data details
   * @param timeStamp                 the time stamp
   * @return the thirty minutes data
   */
  default ThirtyMinutesDataDetails getThirtyMinutesData(
      final String symbol,
      final List<FifteenMinutesDataDetails> fifteenMinutesDataDetails,
      final long timeStamp) {

    final var data = ThirtyMinutesDataDetails.builder()
        .status(RepoEnum.ACTIVE)
        .symbol(symbol)
        .build();

    final var lastStick = fifteenMinutesDataDetails.get(fifteenMinutesDataDetails.size() - 1);
    final var firstStick = fifteenMinutesDataDetails.get(0);

    fifteenMinutesDataDetails
        .forEach(
            fiveMinutesDataDetail -> {
              data.setVendorId(fiveMinutesDataDetail.getVendorId());
              data.setNumberOfTrades(data.getNumberOfTrades() + fiveMinutesDataDetail.getNumberOfTrades());
              data.setLow(Math.min(data.getLow(), fiveMinutesDataDetail.getLow()));
              data.setHigh(Math.max(data.getHigh(), fiveMinutesDataDetail.getHigh()));
              data.setVolume(
                  data.getVolume() + fiveMinutesDataDetail.getVolume());
              data.setQuoteAssestVolume(
                  data.getQuoteAssestVolume() + fiveMinutesDataDetail.getQuoteAssestVolume());
              data.setTakerBuyBaseAssestVolume(
                  data.getTakerBuyBaseAssestVolume() + fiveMinutesDataDetail.getTakerBuyBaseAssestVolume());
              data.setTakerBuyQuoteAssestVolume(
                  data.getTakerBuyQuoteAssestVolume() + fiveMinutesDataDetail.getTakerBuyQuoteAssestVolume());

              if (data.getVolume() == ConfigConstantUtils.DEFAULT_DOUBLE) {
                data.setFailureRate(data.getFailureRate() + 1);
              }
            });

    if (Objects.nonNull(lastStick)) {
      data.setClose(lastStick.getClose());
      data.setCloseTime(lastStick.getCloseTime());
    }
    if (Objects.nonNull(firstStick)) {
      data.setOpen(firstStick.getOpen());
      data.setOpenTime(firstStick.getOpenTime());
    }
    data.setFailureRate(data.getFailureRate() / fifteenMinutesDataDetails.size());
    data.setTimestamp(CommonUtils.getTimeFromMiliToDateInUTC(timeStamp));
    return data;
  }

  /**
   * Gets sixty minutes data.
   *
   * @param symbol                   the symbol
   * @param thirtyMinutesDataDetails the thirty minutes data details
   * @param timeStamp                the time stamp
   * @return the sixty minutes data
   */
  default SixtyMinutesDataDetails getSixtyMinutesData(
      final String symbol,
      final List<ThirtyMinutesDataDetails> thirtyMinutesDataDetails,
      final long timeStamp) {

    final var data = SixtyMinutesDataDetails.builder()
        .status(RepoEnum.ACTIVE)
        .symbol(symbol)
        .build();
    final var lastStick = thirtyMinutesDataDetails.get(thirtyMinutesDataDetails.size() - 1);
    final var firstStick = thirtyMinutesDataDetails.get(0);
    thirtyMinutesDataDetails
        .forEach(
            fiveMinutesDataDetail -> {
              data.setVendorId(fiveMinutesDataDetail.getVendorId());
              data.setNumberOfTrades(data.getNumberOfTrades() + fiveMinutesDataDetail.getNumberOfTrades());
              data.setLow(Math.min(data.getLow(), fiveMinutesDataDetail.getLow()));
              data.setHigh(Math.max(data.getHigh(), fiveMinutesDataDetail.getHigh()));
              data.setVolume(
                  data.getVolume() + fiveMinutesDataDetail.getVolume());
              data.setQuoteAssestVolume(
                  data.getQuoteAssestVolume() + fiveMinutesDataDetail.getQuoteAssestVolume());
              data.setTakerBuyBaseAssestVolume(
                  data.getTakerBuyBaseAssestVolume() + fiveMinutesDataDetail.getTakerBuyBaseAssestVolume());
              data.setTakerBuyQuoteAssestVolume(
                  data.getTakerBuyQuoteAssestVolume() + fiveMinutesDataDetail.getTakerBuyQuoteAssestVolume());

              if (data.getVolume() == ConfigConstantUtils.DEFAULT_DOUBLE) {
                data.setFailureRate(data.getFailureRate() + 1);
              }
            });

    if (Objects.nonNull(lastStick)) {
      data.setClose(lastStick.getClose());
      data.setCloseTime(lastStick.getCloseTime());
    }
    if (Objects.nonNull(firstStick)) {
      data.setOpen(firstStick.getOpen());
      data.setOpenTime(firstStick.getOpenTime());
    }
    data.setFailureRate(data.getFailureRate() / thirtyMinutesDataDetails.size());
    data.setTimestamp(CommonUtils.getTimeFromMiliToDateInUTC(timeStamp));
    return data;
  }
}
