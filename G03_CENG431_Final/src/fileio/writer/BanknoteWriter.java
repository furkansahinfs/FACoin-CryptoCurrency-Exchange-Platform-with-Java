package fileio.writer;

import exception.FileWriteException;
import model.Currency;
import storage.IContainer;

public class BanknoteWriter {

	protected BanknoteWriter() {
	}

	protected String writeBanknotesJson(IContainer<Currency> banknotes) throws FileWriteException {
		String result = "{";
		for(Currency banknote: banknotes) {
			result+=convertBanknoteToJsonString(banknote);
		}
		if (result.endsWith(",")) { // if ends with , ignore it
			result = result.substring(0, result.length() - 1);
		}
		result+="}";
		result = JsonWriteHelper.validateJsonObject(result);
		return result;
	}
	
	private String convertBanknoteToJsonString(Currency banknote) {
		
		return "\""+banknote.getId()+"\":\""+banknote.getName()+"\",";
	}
}
