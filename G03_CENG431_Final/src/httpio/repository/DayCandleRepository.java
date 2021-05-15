package httpio.repository;

import fileio.repository.IRepository;
import storage.IContainer;
import model.Candle;


public class DayCandleRepository implements IRepository<Candle> {

	public DayCandleRepository() {

	}

	
	public IContainer<Candle> day_candles()
	{
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
