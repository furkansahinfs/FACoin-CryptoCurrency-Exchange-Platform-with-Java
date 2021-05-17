package model;

import java.util.Date;
import enums.ECandleStatus;
import factory.objects.CandleParams;

public class HourCandle extends Candle{

	public HourCandle(String coinName, Date candleDate, Date nowDate, String high, String low, String open, String close, String volume, ECandleStatus status) {
		super(coinName, candleDate, nowDate, high, low, open, close, volume, status);
	}
	public HourCandle(CandleParams params){
		super(params);
	}
	
}