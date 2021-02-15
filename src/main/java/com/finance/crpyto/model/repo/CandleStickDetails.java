package com.finance.crpyto.model.repo;

import com.finance.crpyto.enums.RepoEnum;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Repository;

/**
 * The type Candle stick details.
 */
@Builder
@Data
@Repository
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Document("candlestick_details")
public class CandleStickDetails {

  /**
   * The Id.
   */
  @Id
  private String id;
  /**
   * The Vendor id.
   */
  @Field("vendor_id")
  private String vendorId;

  /**
   * The Symbol.
   */
  @Field("symbol")
  private String symbol;

  /**
   * The Open.
   */
  @Field("open")
  private double open;

  /**
   * The High.
   */
  @Field("high")
  private double high;

  /**
   * The Low.
   */
  @Field("low")
  private double low;

  /**
   * The Close.
   */
  @Field("close")
  private double close;

  /**
   * The Timestamp.
   */
  @Field("timestamp")
  private Date timestamp;

  /**
   * The Open time.
   */
  @Field("open_time")
  private Date openTime;

  /**
   * The Close time.
   */
  @Field("close_time")
  private Date closeTime;
  /**
   * The Volume.
   */
  @Field("volume")
  private double volume;
  /**
   * The Number of trades.
   */
  @Field("number_of_trades")
  private int numberOfTrades;
  /**
   * The Taker buy base assest volume.
   */
  @Field("taker_buy_base_assest_volume")
  private double takerBuyBaseAssestVolume;
  /**
   * The Taker buy quote assest volume.
   */
  @Field("taker_buy_quote_assest_volume")
  private double takerBuyQuoteAssestVolume;
  /**
   * The Quote assest volume.
   */
  @Field("quote_assest_volume")
  private double quoteAssestVolume;

  /**
   * The Status.
   */
  @Field("status")
  @Builder.Default
  private RepoEnum status = RepoEnum.ACTIVE;
}
