package fileio.parser;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import exception.FileFormatException;
import factory.BankWalletFactory;
import factory.WalletFactory;
import fileio.repository.BankWalletRepository;
import fileio.repository.IRepository;
import model.BankWallet;

public class BankWalletParser {

	private WalletFactory bankWalletFactory;

	/**
	 * The WalletParser parses the gotten bank_wallets.json file content and creates
	 * wallet objects
	 */
	protected BankWalletParser() {
		bankWalletFactory = new BankWalletFactory();
	}

	/**
	 * The function parses gotten file content and returns the wallet container
	 * which holds created contracts
	 * 
	 * @param fileAll = contract file content
	 * @param users   = user container which holds all users
	 * @param wallets = wallet container which holds all wallets of users
	 * @return Wallet Container
	 * @throws FileFormatException
	 * @throws JSONException
	 */
	protected void parseWallets(String fileAll) throws FileFormatException {
		IRepository<BankWallet> bankWalletRepository = new BankWalletRepository();
		try {
			JSONObject jsonContracts;
			jsonContracts = (new JSONParser()).parse(fileAll); // get json object of file content
			 parse(jsonContracts,bankWalletRepository); // parse wallets
		} catch (JSONException e) {
		}


	}

	private void parse(JSONObject jsonObject, IRepository<BankWallet> bankWalletRepository) throws JSONException, FileFormatException {

		// iterate json object
		Iterator<?> keys = jsonObject.keys();
		Object val = null;
		while (keys.hasNext()) {
			Object keyTemp = keys.next();
			if (!(keyTemp instanceof String))
				throw new JSONException("WalletParser.parse::Key is not a string");
			// get the coin values and invoke createWallet()
			// to get created wallet
			String key = (String) keyTemp;
			val = jsonObject.get(key);
			if (val instanceof JSONObject) {
				JSONArray banknotes = (JSONArray) ((JSONObject) val).get("banknotes");

				Dictionary<String, String> banknote = new Hashtable<String, String>();
				for (int i = 0; i < banknotes.length(); i++) {
					JSONObject coinJson = (JSONObject) banknotes.get(i);
					String coinId = coinJson.getString("id");
					String quantity = coinJson.getString("quantity");
					banknote.put(coinId, quantity);
				}
				BankWallet wallet = createWallet(key, banknote);
				bankWalletRepository.addEntity(wallet);
			}
		}
	}

	private BankWallet createWallet(String walletId, Dictionary<String, String> coins) throws FileFormatException {
		BankWallet createdWallet = (BankWallet) bankWalletFactory.createWallet(walletId, coins);
		if (createdWallet != null) {
			return createdWallet;
		} else {
			throw new FileFormatException("Wrong Format for bank_wallets.json");
		}
	}

}
