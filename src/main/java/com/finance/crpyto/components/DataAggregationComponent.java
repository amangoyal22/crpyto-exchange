package com.finance.crpyto.components;

import com.finance.crpyto.constant.ConfigConstantUtils;
import com.finance.crpyto.constant.DataAggregationConstantUtils;
import com.finance.crpyto.dao.CandleSticksDetailsDao;
import com.finance.crpyto.dao.FifteenMinutesDataDao;
import com.finance.crpyto.dao.FiveMinutesDataDao;
import com.finance.crpyto.dao.SixtyMinutesDataDao;
import com.finance.crpyto.dao.ThirtyMinutesDataDao;
import com.finance.crpyto.mapper.AggregationDataMapper;
import com.finance.crpyto.model.repo.CandleStickDetails;
import com.finance.crpyto.model.repo.FifteenMinutesDataDetails;
import com.finance.crpyto.model.repo.FiveMinutesDataDetails;
import com.finance.crpyto.model.repo.ThirtyMinutesDataDetails;
import com.finance.crpyto.utils.CommonUtils;
import java.util.Calendar;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * The type Data aggregation component.
 */
@Component
@AllArgsConstructor
@Slf4j
public class DataAggregationComponent {

  /**
   * The Candle sticks details dao.
   */
  private final CandleSticksDetailsDao candleSticksDetailsDao;
  /**
   * The Aggregation data mapper.
   */
  private final AggregationDataMapper aggregationDataMapper;
  /**
   * The Five minutes data dao.
   */
  private final FiveMinutesDataDao fiveMinutesDataDao;
  /**
   * The Fifteen minutes data dao.
   */
  private final FifteenMinutesDataDao fifteenMinutesDataDao;
  /**
   * The Thirty minutes data dao.
   */
  private final ThirtyMinutesDataDao thirtyMinutesDataDao;
  /**
   * The Sixty minutes data dao.
   */
  private final SixtyMinutesDataDao sixtyMinutesDataDao;

  /**
   * Run for five mins.
   *
   * @param time the time
   */
  public void runForFiveMins(final long time) {
    try {
      log.info("Five min data for {} - {}",
          CommonUtils.getDateMinus(time, Calendar.MINUTE, DataAggregationConstantUtils.MIN_1_PLUS),
          CommonUtils.getDateMinus(time, Calendar.MINUTE, DataAggregationConstantUtils.MIN_5_MINUS));
      candleSticksDetailsDao.findByTimeStamp(
          CommonUtils.getDateMinus(time, Calendar.MINUTE, DataAggregationConstantUtils.MIN_1_PLUS),
          CommonUtils.getDateMinus(time, Calendar.MINUTE, DataAggregationConstantUtils.MIN_5_MINUS))
          .stream()
          .collect(Collectors.groupingBy(CandleStickDetails::getSymbol))
          .forEach((symbol, candleStickDetails) ->
              fiveMinutesDataDao.save(
                  aggregationDataMapper.getFiveMinutesData(symbol, candleStickDetails, time)));
      if (CommonUtils.getUTCMinutes(time) % DataAggregationConstantUtils.MIN_15
          == ConfigConstantUtils.DEFAULT_INTEGER) {
        runForFifteenMins(time);
      }
    } catch (final Exception exp) {
      log.error("Error while Updating the Aggregating 5 min: {}", exp.getMessage());
    }
  }

  /**
   * Run for fifteen mins.
   *
   * @param time the time
   */
  public void runForFifteenMins(final long time) {
    try {
      log.info("Fifteen min data for {} - {}",
          CommonUtils.getDateMinus(time, Calendar.MINUTE, DataAggregationConstantUtils.MIN_1_PLUS),
          CommonUtils.getDateMinus(time, Calendar.MINUTE, DataAggregationConstantUtils.MIN_15_MINUS));
      fiveMinutesDataDao.findByTimeStamp(
          CommonUtils.getDateMinus(time, Calendar.MINUTE, DataAggregationConstantUtils.MIN_1_PLUS),
          CommonUtils.getDateMinus(time, Calendar.MINUTE, DataAggregationConstantUtils.MIN_15_MINUS))
          .stream()
          .collect(Collectors.groupingBy(FiveMinutesDataDetails::getSymbol))
          .forEach((symbol, fiveMinutesDataDetails) ->
              fifteenMinutesDataDao.save(
                  aggregationDataMapper.getFifteenMinutesData(symbol, fiveMinutesDataDetails, time)));

      if (CommonUtils.getUTCMinutes(time) % DataAggregationConstantUtils.MIN_30
          == ConfigConstantUtils.DEFAULT_INTEGER) {
        runForThirtyMins(time);
      }
    } catch (final Exception exp) {
      log.error("Error while Updating the Aggregating 15 min: {}", exp.getMessage());
    }
  }

  /**
   * Run for thirty mins.
   *
   * @param time the time
   */
  public void runForThirtyMins(final long time) {
    try {
      log.info("Thirty min data for {} - {}",
          CommonUtils.getDateMinus(time, Calendar.MINUTE, DataAggregationConstantUtils.MIN_1_PLUS),
          CommonUtils.getDateMinus(time, Calendar.MINUTE, DataAggregationConstantUtils.MIN_16_MINUS));
      fifteenMinutesDataDao.findByTimeStamp(
          CommonUtils.getDateMinus(time, Calendar.MINUTE, DataAggregationConstantUtils.MIN_1_PLUS),
          CommonUtils.getDateMinus(time, Calendar.MINUTE, DataAggregationConstantUtils.MIN_16_MINUS))
          .stream()
          .collect(Collectors.groupingBy(FifteenMinutesDataDetails::getSymbol))
          .forEach((symbol, fifteenMinutesDataDetails) ->
              thirtyMinutesDataDao.save(
                  aggregationDataMapper.getThirtyMinutesData(symbol, fifteenMinutesDataDetails, time)));

      if (CommonUtils.getUTCMinutes(time) % DataAggregationConstantUtils.MIN_60
          == ConfigConstantUtils.DEFAULT_INTEGER) {
        runForSixtyMins(time);
      }
    } catch (final Exception exp) {
      log.error("Error while Updating the Aggregating 30 min: {}", exp.getMessage());
    }
  }

  /**
   * Run for sixty mins.
   *
   * @param time the time
   */
  public void runForSixtyMins(final long time) {
    try {
      log.info("Sixty min data for {} - {}",
          CommonUtils.getDateMinus(time, Calendar.MINUTE, DataAggregationConstantUtils.MIN_1_PLUS),
          CommonUtils.getDateMinus(time, Calendar.MINUTE, DataAggregationConstantUtils.MIN_31_MINUS));
      thirtyMinutesDataDao.findByTimeStamp(
          CommonUtils.getDateMinus(time, Calendar.MINUTE, DataAggregationConstantUtils.MIN_1_PLUS),
          CommonUtils.getDateMinus(time, Calendar.MINUTE, DataAggregationConstantUtils.MIN_31_MINUS))
          .stream()
          .collect(Collectors.groupingBy(ThirtyMinutesDataDetails::getSymbol))
          .forEach((symbol, thirtyMinutesDataDetails) ->
              sixtyMinutesDataDao.save(
                  aggregationDataMapper.getSixtyMinutesData(symbol, thirtyMinutesDataDetails, time)));
    } catch (final Exception exp) {
      log.error("Error while Updating the Aggregating 60 min: {}", exp.getMessage());
    }
  }
}
