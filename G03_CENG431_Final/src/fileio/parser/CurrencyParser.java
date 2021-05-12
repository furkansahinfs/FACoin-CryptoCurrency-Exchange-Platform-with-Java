package fileio.parser;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import exception.FileFormatException;
import factory.CryptoWalletFactory;
import factory.CurrencyFactory;
import factory.WalletFactory;
import storage.CurrencyContainer;
import storage.IContainer;
import storage.WalletContainer;
import model.Currency;
import model.Wallet;

public class CurrencyParser {

	private CurrencyFactory currencyFactory;

	/**
	 * The CurrencyParser parses the gotten currencies.json file content and creates
	 * currency objects
	 */
	protected CurrencyParser() {
		currencyFactory = new CurrencyFactory();
	}

	/**
	 * The function parses gotten file content and returns the currency container
	 * which holds created currencies
	 * 
	 * @param fileAll    = contract file content
	 * @param users      = user container which holds all users
	 * @param currencies = currency container which holds all currencies in the
	 *                   system
	 * @return Currency Container
	 * @throws FileFormatException
	 * @throws JSONException
	 */
	protected IContainer<Currency> parseCurrencies(String fileAll) throws FileFormatException {
		IContainer<Currency> currencies = null;
		try {
			JSONObject jsonCurrencies;
			jsonCurrencies = (new JSONParser()).parse(fileAll); // get json object of file content
			currencies = parse(jsonCurrencies); // parse currencies
		} catch (JSONException e) {
		}
		return currencies;
	}

	private IContainer<Currency> parse(JSONObject jsonObject) throws JSONException, FileFormatException {
		IContainer<Currency> currencies = new CurrencyContainer();
		// iterate json object
		Iterator<?> keys = jsonObject.keys();
		Object val = null;
		while (keys.hasNext()) {
			Object keyTemp = keys.next();
			if (!(keyTemp instanceof String))
				throw new JSONException("WalletParser.parse::Key is not a string");
			// get the coin values and invoke createCurrency()
			// to get created currency
			String key = (String) keyTemp;
			val = jsonObject.get(key);
			Currency currency = createCurrency(key, (String) val);
			currencies.add(currency);
		}
		return currencies;
	}

	private Currency createCurrency(String currencyId, String currencyName) throws FileFormatException
	{
		Currency createdCurrency = currencyFactory.
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
