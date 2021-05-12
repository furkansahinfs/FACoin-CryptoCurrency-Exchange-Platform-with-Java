package factory;

import model.ICurrency;

public class CurrencyFactory extends AbstractFactory{
	
	@Override
	public Object createEntity(AbstractFactory factory, Object args){
		
		ICurrency currency = null;
		
		if(!(args instanceof CurrencyFactoryParams)){
			return currency;
		}
		
		CurrencyFactoryParams params = (CurrencyFactoryParams)args;
		
		
		
		return factory.createEntity(factory,args);
	}
	
}