package httpio.repository;


import model.Candle;
import storage.IContainer;

public class HttpRepository {

	// FileController to read data, write data and get read content of files.
	private static HttpController httpController;

	public HttpRepository() {
		httpController = new HttpController();
	}

	
	protected static final IContainer<Candle> day_candles() {
		return httpController.dayCandles();
	}

	
	protected static final IContainer<Candle> hour_candles() {
		return httpController.hourCandles();
	}
	
}
