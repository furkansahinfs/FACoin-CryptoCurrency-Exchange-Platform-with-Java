package model;

import java.util.Date;

import enums.CandleStatus;

public abstract class Candle{
	private Date candleDate, nowDate;
	private String coinName, high,low,open,close,volume;
	private CandleStatus status;
	
	public Candle(CandleParams params){
		this(params.coinName,params.candleDate, params.nowDate,params.high,params.low, params.open, params.close,params.volume, params.status);
	}
	

	public Candle(String coinName, Date candleDate, Date nowDate, String high, String low, String open, String close, String volume, CandleStatus status){
		this.coinName = coinName;
		this.candleDate =candleDate;
		this.nowDate = nowDate;
		this.high=high;
		this.low=low;
		this.open = open;
		this.close = close; 
		this.volume = volume;
		this.status = status;
	}
	
	public CandleStatus getStatus() {
		return status;
	}


	public void setStatus(CandleStatus status) {
		this.status = status;
	}


	public String getCoinName() {
		return coinName;
	}
	
	public Date getCandleDate() {
		return candleDate;
	}
	
	public Date getNowDate() {
		return nowDate;
	}


	public String getHigh() {
		return high;
	}

	public String getLow() {
		return low;
	}

	public String getOpen() {
		return open;
	}

	public String getClose() {
		return close;
	}

	public String getVolume() {
		return volume;
	} 
}