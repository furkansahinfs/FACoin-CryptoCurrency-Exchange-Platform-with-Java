 package httpio.repository;

import java.util.Date;

import httpio.HttpIO;
import httpio.IHttpIO;
import model.Candle;
import settings.AppSettings;
import storage.IContainer;
import storage.CandleContainer;

public class HttpController {
	private static IHttpIO httpIO = new HttpIO();
	
	private IContainer<Candle> dayCandles;
	private IContainer<Candle> hourCandles;
	
	public HttpController(){
		dayCandles = new CandleContainer();
		hourCandles = new CandleContainer();
		
	}
	
	public static Date getDate(){
		Date date = null;
		date = httpIO.getTime(AppSettings.DATE_ENDPOINT); 
		return date;
	}
	
	protected IContainer<Candle> dayCandles(){
		return this.dayCandles;
	}
	protected IContainer<Candle> hourCandles(){
		return this.hourCandles;
	}
}
