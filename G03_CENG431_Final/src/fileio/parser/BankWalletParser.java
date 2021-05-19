package fileio.parser;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import exception.FileReadException;
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
	 * @throws FileReadException
	 * @throws JSONException
	 */
	protected void parseWallets(String fileAll) throws FileReadException, JSONException {
		IRepository<BankWallet> bankWalletRepository = new BankWalletRepository();
		JSONObject jsonContracts;
		jsonContracts = (new JSONParser()).parse(fileAll); // get json object of file content
		parse(jsonContracts, bankWalletRepository); // parse wallets


	}

	private void parse(JSONObject jsonObject, IRepository<BankWallet> bankWalletRepository)
			throws JSONException, FileReadException {

		// iterate json object
		Iterator<?> keys = jsonObject.keys();
		Object val = null;
		while (keys.hasNext()) {
			Object keyTemp = keys.next();
			if (!(keyTemp instanceof String))
				throw new JSONException("BankWalletParser.parse::Key is not a string");
			// get the coin values and invoke createWallet()
			// to get created wallet
			String key = (String) keyTemp;
			val = jsonObject.get(key);
			if (val instanceof JSONObject) {
				JSONArray banknotes = (JSONArray) ((JSONObject) val).get("banknotes");

				Dictionary<String, String> banknote = new Hashtable<String, String>();
				for (int i = 0; i < banknotes.length(); i++) {
					JSONObject banknoteJson = (JSONObject) banknotes.get(i);
					String banknoteId = banknoteJson.getString("id");
					String quantity = banknoteJson.getString("quantity");
					banknote.put(banknoteId, quantity);
				}
				BankWallet wallet = createWallet(key, banknote);
				bankWalletRepository.addEntity(wallet);
			}
		}
	}

	private BankWallet createWallet(String walletId, Dictionary<String, String> banknotes) throws FileReadException {
		BankWallet createdWallet = (BankWallet) bankWalletFactory.createWallet(walletId, banknotes);
		if (createdWallet != null) {
			return createdWallet;
		} else {
			throw new FileReadException("Wrong Format for bank_wallets.json");
		}
	}

}
