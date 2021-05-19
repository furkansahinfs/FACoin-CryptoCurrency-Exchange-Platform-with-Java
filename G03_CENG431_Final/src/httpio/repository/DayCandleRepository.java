package httpio.repository;

import exception.HttpRequestException;
import fileio.repository.IRepository;
import storage.IContainer;
import model.Candle;


public class DayCandleRepository implements IRepository<Candle> {

	public DayCandleRepository() {

	}

	
	public IContainer<Candle> day_candles(String coinName, String banknoteName)
	{
		try {
			HttpRepository.initDayCandles(coinName, banknoteName);
		} catch (HttpRequestException e) {
			
		}
		return HttpRepository.day_candles();
	}
	
	public boolean addEntity(Candle candle)
	{
		return HttpRepository.day_candles().add(candle);
	}
	
	public Candle removeEntity(Candle candle)
	{
		return null;
	}

}
