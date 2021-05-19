package httpio.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import enums.EBanknotes;
import enums.ECoins;
import exception.FileReadException;
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
	 * @throws FileReadException
	 */

	protected List<UpdateData> parseValues(String endpointResult) {
		JSONObject jsonValues;

		List<UpdateData> updateDataList = null;
		try {
			jsonValues = (new JSONParser()).parse(endpointResult); // get json object of file content
			updateDataList = parse(jsonValues);
		} catch (JSONException e) {
			updateDataList = null;
		}
		return updateDataList;
	}

	private List<UpdateData> parse(JSONObject jsonObject) throws JSONException {
		// iterate json object
		List<UpdateData> updateDataList = new ArrayList<UpdateData>();
		// UpdateData[] updateDataList = {};
		Iterator<?> coinsIterator = jsonObject.keys();
		JSONObject currencyValues = null;
		while (coinsIterator.hasNext()) {
			Object coinKey = coinsIterator.next();
			if (!(coinKey instanceof String))
				throw new JSONException("WalletParser.parse::Key is not a string");

			// get the banknote values and invoke createCurrency()
			// to get created banknotes
			String coinName = (String) coinKey;
			if (!coinName.equals("Error")) {

				currencyValues = (JSONObject) jsonObject.get(coinName);
				Iterator<?> valuesIterator = ((JSONObject) currencyValues).keys();
				Object banknoteValue = null;
				UpdateData updateData = new UpdateData(coinName);
				while (valuesIterator.hasNext()) {
					Object valueKey = valuesIterator.next();
					if (!(valueKey instanceof String))
						throw new JSONException("UpdateParser.parse::Key is not a string");
					// get the banknote values and invoke createCurrency()
					// to get created banknotes
					String banknoteName = (String) valueKey;
					banknoteValue = currencyValues.get(banknoteName);
					if (validateResult(coinName, banknoteName, banknoteValue)) {
						updateData.addKeyValue(banknoteName, ((Double) banknoteValue).floatValue());
					}
				}
				updateDataList.add(updateData);
			}
		}

		return updateDataList;
	}

	private boolean validateResult(String coinName, String banknoteName, Object banknoteValue) {
		boolean isValidCoin = ECoins.isCoin(coinName);
		boolean isValidBanknote = EBanknotes.isBanknote(banknoteName);
		boolean isValidFloat;
		try {
			((Double) banknoteValue).floatValue();
			isValidFloat = true;
		} catch (Exception e) {
			isValidFloat = false;
		}

		return (isValidCoin && isValidBanknote && isValidFloat);
	}
}
