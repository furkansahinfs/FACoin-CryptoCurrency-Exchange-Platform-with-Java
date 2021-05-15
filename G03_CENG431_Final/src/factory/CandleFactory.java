package factory;


public abstract class CandleFactory extends AbstractFactory {
	
	public CandleFactory(CandleFactory factory) {
		super(factory);
	}

	@Override
	public abstract Object createEntity(Object args);
	
}