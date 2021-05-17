package fileio.parser;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import exception.FileFormatException;
import factory.AbstractFactory;
import factory.CoinFactory;
import factory.CurrencyFactoryParams;
import fileio.repository.CoinRepository;
import fileio.repository.IRepository;
import model.Coin;
import model.Currency;

public class CoinParser {

	private AbstractFactory abstractFactory;

	/**
	 * The CoinParser parses the gotten coins.json file content and creates
	 * coin objects
	 */
	protected CoinParser() {
		abstractFactory = new AbstractFactory(new CoinFactory());
	}

	/**
	 * The function parses gotten file content and creates coin objects
	 * 
	 * @param fileAll    = banknotes.json file content	
	 * @throws FileFormatException
	 */
	protected void parseCurrencies(String fileAll) throws FileFormatException {
		IRepository<Currency> currencyRepository = new CoinRepository();
		try {
			JSONObject jsonCurrencies;
			jsonCurrencies = (new JSONParser()).parse(fileAll); // get json object of file content
			parse(jsonCurrencies, currencyRepository); // parse coins
		} catch (JSONException e) {
		}
	}

	private void parse(JSONObject jsonObject, IRepository<Currency> currencyRepository) throws JSONException, FileFormatException {
		// iterate json object
		Iterator<?> keys = jsonObject.keys();
		Object val = null;
		while (keys.hasNext()) {
			Object keyTemp = keys.next();
			if (!(keyTemp instanceof String))
				throw new JSONException("CoinParser.parse::Key is not a string");
			// get the coin values and invoke createCurrency()
			// to get created currency
			String key = (String) keyTemp;
			val = jsonObject.get(key);
			Coin coin = createCurrency(key, (String) val);
			currencyRepository.addEntity(coin);
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
			throw new FileFormatException("Wrong Format for coins.json");
		}		
	}

}
