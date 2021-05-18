package factory.objects;

import java.util.Date;

import enums.ECandleStatus;

public class CandleParams{

	public Date candleDate, nowDate;
	public String coinName, high,low,open,close,volume;
	public ECandleStatus status;

	public CandleParams(String coinName, Date date, Date nowDate, String high, String low, String open, String close, String volume,ECandleStatus status){
		this.coinName = coinName;
		this.candleDate = date;
		this.nowDate = nowDate;
		this.high = high;
		this.low = low;
		this.open = open;
		this.close = close;
		this.volume = volume;
		this.status = status;
	}	
}