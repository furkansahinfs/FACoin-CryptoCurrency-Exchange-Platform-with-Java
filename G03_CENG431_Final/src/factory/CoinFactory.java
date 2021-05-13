package factory;

import factory.validator.CoinValidator;
import factory.validator.ValidationResult;
import fileio.repository.CoinRepository;
import fileio.repository.IRestrictedRepository;
import model.Coin;
import model.Currency;
import model.ICurrency;

public class CoinFactory extends CurrencyFactory {

	@Override
	public Object createEntity(Object args) {
		ICurrency currency = null;
		if(!(args instanceof CurrencyFactoryParams)){
			return currency;
		}
		
		CurrencyFactoryParams params = (CurrencyFactoryParams)args;
		ValidationResult vr = CoinValidator.validatCoin(params.name, params.id);
		if(vr.isValid){
			currency = new Coin(params.id,0,params.name);
		}
		else if(vr.messages.contains("Id is not validated.") && vr.messages.length() == 20)
		{
			IRestrictedRepository<Currency> repository = new CoinRepository();
			String new_id = RandomFactory.randomId();
			while (repository.getById(new_id).getObject() != null) {
				new_id = RandomFactory.randomId();
			}
			
			currency = new Coin(new_id,0, params.name);
		}
		
		return currency;
	}

}
