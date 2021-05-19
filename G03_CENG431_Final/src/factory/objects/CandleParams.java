package factory.objects;

import java.util.Date;

public class CandleParams{

	public Date candleDate;
	public Double high,low,open,close,volume;

	public CandleParams(Date date, Double high, Double low, Double open, Double close, Double volume){
		this.candleDate = date;
		this.high = high;
		this.low = low;
		this.open = open;
		this.close = close;
		this.volume = volume;
	}	
}