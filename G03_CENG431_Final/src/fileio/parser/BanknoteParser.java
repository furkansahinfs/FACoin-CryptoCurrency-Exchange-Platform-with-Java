package fileio.parser;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import exception.FileFormatException;
import factory.AbstractFactory;
import factory.BanknoteFactory;
import factory.CurrencyFactoryParams;
import fileio.repository.CoinRepository;
import fileio.repository.IRepository;
import model.Coin;
import model.Currency;

public class BanknoteParser {

	private AbstractFactory abstractFactory;

	/**
	 * The BanknoteParser parses the gotten banknotes.json file content and creates
	 * banknote objects
	 */
	protected BanknoteParser() {
		abstractFactory = new AbstractFactory(new BanknoteFactory());
	}

	/**
	 * The function parses gotten file content creates banknote objects.
	 * 
	 * @param fileAll    = banknotes.json file content	
	 * @throws FileFormatException
	 */
	protected void parseCurrencies(String fileAll) throws FileFormatException {
		IRepository<Currency> banknoteRepository = new CoinRepository();
		try {
			JSONObject jsonCurrencies;
			jsonCurrencies = (new JSONParser()).parse(fileAll); // get json object of file content
			parse(jsonCurrencies, banknoteRepository); // parse banknotes
		} catch (JSONException e) {
		}
	}

	private void parse(JSONObject jsonObject, IRepository<Currency> banknoteRepository) throws JSONException, FileFormatException {
		// iterate json object
		Iterator<?> keys = jsonObject.keys();
		Object val = null;
		while (keys.hasNext()) {
			Object keyTemp = keys.next();
			if (!(keyTemp instanceof String))
				throw new JSONException("WalletParser.parse::Key is not a string");
			// get the banknote values and invoke createCurrency()
			// to get created banknotes
			String key = (String) keyTemp;
			val = jsonObject.get(key);
			Coin coin = createCurrency(key, (String) val);
			banknoteRepository.addEntity(coin);
		}
	}

	private Coin createCurrency(String currencyId, String currencyName) throws FileFormatException
	{
		CurrencyFactoryParams params = new CurrencyFactoryParams(currencyName, currencyId);
		Coin createdCurrency = 	(Coin) abstractFactory.createEntity(params);
		if(createdCurrency != null)
		{
			return createdCurrency;
		}
		else
		{
			throw new FileFormatException("Wrong Format for crypto_wallet.json");
		}		
	}

}