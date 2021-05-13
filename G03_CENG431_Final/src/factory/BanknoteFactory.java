package factory;

import factory.validator.BanknoteValidator;
import factory.validator.ValidationResult;
import fileio.repository.BanknoteRepository;
import fileio.repository.IRestrictedRepository;
import model.Banknote;
import model.Currency;
import model.ICurrency;

public class BanknoteFactory extends CurrencyFactory {

	@Override
	public Object createEntity(Object args) {
		ICurrency currency = null;
		if(!(args instanceof CurrencyFactoryParams)){
			return currency;
		}
		
		CurrencyFactoryParams params = (CurrencyFactoryParams)args;
		ValidationResult vr = BanknoteValidator.validateBanknote(params.name, params.id);
		if(vr.isValid){
			currency = new Banknote(params.id,0, params.name);
		}
		else if(vr.messages.contains("Id is not validated.") && vr.messages.length() == 20)
		{
			String new_id = RandomFactory.randomId();
			IRestrictedRepository<Currency> repository = new BanknoteRepository();
			while (repository.getById(new_id).getObject() != null) {
				new_id = RandomFactory.randomId();
			}
			
			currency = new Banknote(new_id,0, params.name);
		}
	
		return currency;
	}

}
