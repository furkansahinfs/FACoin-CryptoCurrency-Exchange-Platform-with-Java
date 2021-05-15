package storage;

import exception.NotSupportedException;
import model.Candle;

public class CandleContainer extends Container<Candle> {

	@Override
	public Candle getById(String id) throws NotSupportedException {
		throw new NotSupportedException();
	}

	@Override
	public Candle getByName(String status) throws  NotSupportedException {
		throw new NotSupportedException();
	}

}
