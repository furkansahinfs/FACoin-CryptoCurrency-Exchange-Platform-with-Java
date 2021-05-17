package fileio.parser;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import exception.FileFormatException;
import factory.CryptoWalletFactory;
import factory.WalletFactory;
import fileio.repository.CryptoWalletRepository;
import fileio.repository.IRepository;
import model.CryptoWallet;

public class CryptoWalletParser {

	private WalletFactory cryptoWalletFactory;

	/**
	 * The WalletParser parses the gotten crypto_wallets.json file content and
	 * creates wallet objects
	 */
	protected CryptoWalletParser() {
		cryptoWalletFactory = new CryptoWalletFactory();
	}

	/**
	 * The function parses gotten file content and returns the wallet container
	 * which holds created wallets
	 * 
	 * @param fileAll = contract file content
	 * @param users   = user container which holds all users
	 * @param wallets = wallet container which holds all wallets of users
	 * @return Wallet Container
	 * @throws FileFormatException
	 * @throws JSONException
	 */
	protected void parseWallets(String fileAll) throws FileFormatException {
		IRepository<CryptoWallet> cryptoWalletRepository = new CryptoWalletRepository();
		try {
			JSONObject jsonContracts;
			jsonContracts = (new JSONParser()).parse(fileAll); // get json object of file content
			parse(jsonContracts, cryptoWalletRepository); // parse wallets
		} catch (JSONException e) {
		}
	}

	private void parse(JSONObject jsonObject, IRepository<CryptoWallet> cryptoWalletRepository)
			throws JSONException, FileFormatException {

		// iterate json object
		Iterator<?> keys = jsonObject.keys();
		Object val = null;
		while (keys.hasNext()) {
			Object keyTemp = keys.next();
			if (!(keyTemp instanceof String))
				throw new JSONException("CryptoWalletParser.parse::Key is not a string");
			// get the coin values and invoke createWallet()
			// to get created wallet
			String key = (String) keyTemp;
			val = jsonObject.get(key);
			if (val instanceof JSONObject) {
				JSONArray coins = (JSONArray) ((JSONObject) val).get("coins");

				Dictionary<String, String> coinDictionary = new Hashtable<String, String>();
				for (int i = 0; i < coins.length(); i++) {
					JSONObject coinJson = (JSONObject) coins.get(i);
					String coinId = coinJson.getString("id");
					String quantity = coinJson.getString("quantity");
					coinDictionary.put(coinId, quantity);
				}
				CryptoWallet wallet = createWallet(key, coinDictionary);
				cryptoWalletRepository.addEntity(wallet);
			}
		}
	}

	private CryptoWallet createWallet(String walletId, Dictionary<String, String> coins) throws FileFormatException {

		CryptoWallet createdWallet = (CryptoWallet) cryptoWalletFactory.createWallet(walletId, coins);
		if (createdWallet != null) {
			return createdWallet;
		} else {
			throw new FileFormatException("Wrong Format for crypto_wallet.json");
		}
	}

}
