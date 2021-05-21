package storage;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import model.Candle;

public class CandleContainer extends Container<Candle> {

	@Override
	public Candle getById(String id) throws NotSupportedException {
		throw new NotSupportedException();
	}

	@Override
	public Candle getByName(Name name) throws  NotSupportedException {
		throw new NotSupportedException();
	}

	@Override
	public Candle getByName(String name) throws ItemNotFoundException, NotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}


}
