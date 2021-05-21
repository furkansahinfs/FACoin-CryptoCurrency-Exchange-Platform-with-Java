package httpio.repository;

import exception.HttpRequestException;
import fileio.repository.DatabaseResult;
import fileio.repository.IRepository;
import storage.IContainer;
import model.Candle;


public class HourCandleRepository implements IRepository<Candle> {

	public HourCandleRepository() {

	}

	
	public IContainer<Candle> hour_candles(String coinName, String banknoteName)
	{
		try {
			HttpRepository.initHourCandles(coinName, banknoteName);
		} catch (HttpRequestException e) {
		}
		return HttpRepository.hour_candles();
	}
	
	public boolean addEntity(Candle candle)
	{
		return HttpRepository.hour_candles().add(candle);
	}
	
	public Candle removeEntity(Candle candle)
	{
		return null;
	}


	@Override
	public DatabaseResult saveChanges() {
		// TODO Auto-generated method stub
		return null;
	}

}
