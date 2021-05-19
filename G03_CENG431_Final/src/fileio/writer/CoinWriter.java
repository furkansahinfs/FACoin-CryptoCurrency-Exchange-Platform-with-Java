package fileio.writer;

import exception.FileWriteException;
import model.Currency;
import storage.IContainer;

public class CoinWriter {

	protected CoinWriter() {
	}

	protected String writeCoinsJson(IContainer<Currency> coins) throws FileWriteException {
		String result = "{";
		for(Currency coin: coins) {
			result+=convertCoinToJsonString(coin);
		}
		if (result.endsWith(",")) { // if ends with , ignore it
			result = result.substring(0, result.length() - 1);
		}
		result+="}";
		result = JsonWriteHelper.validateJsonObject(result);
		return result;
	}
		
	private String convertCoinToJsonString(Currency coin) {
		
		return "\""+coin.getId()+"\":\""+coin.getName()+"\",";
	}
}