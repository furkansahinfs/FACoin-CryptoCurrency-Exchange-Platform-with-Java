 package httpio.repository;

import java.util.Date;

import exception.HttpRequestException;
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
	
	public void readDayCandles(String coinName,String banknoteName) throws HttpRequestException
	{
		dayCandles.clear();
		httpIO.readDayCandles(String.format(AppSettings.DAY_CANDLE_ENDPOINT, coinName,banknoteName));
		
	}
	
	public void readHourCandles(String coinName,String banknoteName) throws HttpRequestException
	{
		hourCandles.clear();
		httpIO.readHourCandles(String.format(AppSettings.HOUR_CANDLE_ENDPOINT, coinName,banknoteName));
	} 
	
	
	protected static Date updateDate(){
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
