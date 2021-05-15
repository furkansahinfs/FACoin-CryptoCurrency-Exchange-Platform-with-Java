package model;

import java.util.Date;
import enums.CandleStatus;

public class HourCandle extends Candle{

	public HourCandle(String coinName, Date candleDate, Date nowDate, String high, String low, String open, String close, String volume, CandleStatus status) {
		super(coinName, candleDate, nowDate, high, low, open, close, volume, status);
	}
	public HourCandle(CandleParams params){
		super(params);
	}
	
}