package model;

import java.util.Date;

import enums.CandleStatus;

public class DayCandle extends Candle{

	public DayCandle(String coinName, Date candleDate, Date nowDate, String high, String low,String open, String close, String volume,CandleStatus status) {
		super(coinName, candleDate, nowDate, high, low, open, close, volume, status);
	}
	public DayCandle(CandleParams params){
		super(params);
	}
}