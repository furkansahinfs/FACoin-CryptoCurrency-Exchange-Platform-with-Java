package httpio.parser;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import enums.Banknotes;
import enums.Coins;
import exception.FileFormatException;
import fileio.parser.JSONParser;
import fileio.repository.UpdateData;

public class UpdateParser {

	/**
	 * The BanknoteParser parses the gotten banknotes.json file content and creates
	 * banknote objects
	 */
	protected UpdateParser() {
	}

	/**
	 * The function parses gotten file content creates banknote objects.
	 * 
	 * @param fileAll = banknotes.json file content
	 * @throws FileFormatException
	 */

	protected UpdateData[] parseValues(String endpointResult) {
		JSONObject jsonValues;

		UpdateData[] newValues;
		try {
			jsonValues = (new JSONParser()).parse(endpointResult); // get json object of file content
			newValues = parse(jsonValues);
		} catch (JSONException e) {
			newValues = null;
		}
		return newValues;
	}

	private UpdateData[] parse(JSONObject jsonObject) throws JSONException {
		// iterate json object
		UpdateData[] updateDataList = {};
		Iterator<?> coinsIterator = jsonObject.keys();
		Object currencyValues = null;
		while (coinsIterator.hasNext()) {
			Object coinKey = coinsIterator.next();
			if (!(coinKey instanceof String))
				throw new JSONException("WalletParser.parse::Key is not a string");

			// get the banknote values and invoke createCurrency()
			// to get created banknotes
			String coinName = (String) coinKey;
			if (!coinName.equals("Error")) {

				currencyValues = jsonObject.get(coinName);
				Iterator<?> valuesIterator = ((JSONObject) currencyValues).keys();
				Object banknoteValue = null;
				UpdateData updateData = new UpdateData(coinName);
				while (valuesIterator.hasNext()) {
					Object valueKey = valuesIterator.next();
					if (!(valueKey instanceof String))
						throw new JSONException("WalletParser.parse::Key is not a string");
					// get the banknote values and invoke createCurrency()
					// to get created banknotes
					String banknoteName = (String) valueKey;
					banknoteValue = jsonObject.get(banknoteName);
					if (validateResult(coinName, banknoteName, banknoteValue)) {
						updateData.addKeyValue(banknoteName, Float.valueOf((String) banknoteValue));
					}
				}
				updateDataList[updateDataList.length] = updateData;
			}
		}
		return updateDataList;
	}

	private boolean validateResult(String coinName, String banknoteName, Object banknoteValue) {
		boolean isValidCoin = Coins.isCoin(coinName);
		boolean isValidBanknote = Banknotes.isBanknote(banknoteName);
		boolean isValidFloat;
		try {
			Float.valueOf((String) banknoteValue);
			isValidFloat = true;
		} catch (Exception e) {
			isValidFloat = false;
		}

		return (isValidCoin && isValidBanknote && isValidFloat);
	}
}
