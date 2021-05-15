package httpio.repository;

import fileio.repository.IRepository;
import storage.IContainer;
import model.Candle;


public class HourCandleRepository implements IRepository<Candle> {

	public HourCandleRepository() {

	}

	
	public IContainer<Candle> hour_candles()
	{
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

}
