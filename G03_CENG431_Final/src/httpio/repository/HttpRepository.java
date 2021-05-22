package httpio.repository;


import exception.HttpRequestException;
import model.Candle;
import storage.IContainer;

public class HttpRepository{

	// FileController to read data, write data and get read content of files.
	private static HttpController httpController;

	public HttpRepository() {
		httpController = new HttpController();
	}
	
	public void initHttpDatabase() {
		
	}

	protected static void initHourCandles(String coinName, String banknoteName) throws HttpRequestException {
		httpController.readHourCandles(coinName, banknoteName);
	}
	
	protected static void initDayCandles(String coinName, String banknoteName) throws HttpRequestException {
		httpController.readDayCandles(coinName, banknoteName);
	}
	
	protected static final IContainer<Candle> day_candles() {
		return httpController.dayCandles();
	}

	
	protected static final IContainer<Candle> hour_candles() {
		return httpController.hourCandles();
	}
	
}
