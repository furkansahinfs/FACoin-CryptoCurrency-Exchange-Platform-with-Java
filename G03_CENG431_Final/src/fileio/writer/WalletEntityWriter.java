package fileio.writer;

import model.Currency;
import model.WalletEntity;
import storage.IContainer;

public class WalletEntityWriter {

	protected static String writeWalletEntitiesToJsonArray(String entityNames, IContainer<WalletEntity> entities) {
		String result = "{\""+entityNames+"\": [";
		for(WalletEntity entity: entities) {
			result+=writeWalletEntityToJsonObject(entity);
		}
		if (result.endsWith(",")) { // if ends with , ignore it
			result = result.substring(0, result.length() - 1);
		}
		result+="]}";
		return result;
	}
	
	private static String writeWalletEntityToJsonObject(WalletEntity entity) {
		Currency currency = (Currency) entity.getCurrency();
		String quantity = String.valueOf(entity.getQuantity());
		return "{\"id\":\""+currency.getId()+"\",\"quantity\":\""+quantity+"\"},";
	}
}
