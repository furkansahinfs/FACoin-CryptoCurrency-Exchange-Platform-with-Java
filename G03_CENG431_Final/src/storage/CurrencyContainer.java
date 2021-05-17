package storage;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import model.Currency;

public class CurrencyContainer extends Container<Currency> {

	@Override
	public Currency getById(String id) throws ItemNotFoundException {
		Currency found = null;
		for (Currency currency : this.getContainer()) {
			if (currency.equals(id)) {
				found = currency;
				break;
			}
		}
		if (found == null) {
			throw new ItemNotFoundException("There is no curreny has id " + id);
		} else {
			return found;
		}
	}

	@Override
	public Currency getByName(String name) throws ItemNotFoundException, NotSupportedException {
		Currency found = null;
		Name nameObject = new Name(name);
		for (Currency currency : this.getContainer()) {
			
			if (currency.equals(nameObject)) {
				found = currency;
				break;
			}
		}
		if (found == null) {
			throw new ItemNotFoundException("There is no curreny has name " + name);
		} else {
			return found;
		}
	}

	@Override
	public String toString(int value) {
		String currencies = "";
		for (Currency currency : this.getContainer()) {
			currencies += currency.getName() + ",";
		}
		if (currencies.endsWith(",")) { // if ends with , ignore it
			currencies = currencies.substring(0, currencies.length() - 1);
		}
		return currencies;
	}

}
