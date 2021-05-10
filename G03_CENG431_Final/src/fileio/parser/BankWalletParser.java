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
import storage.IContainer;
import storage.WalletContainer;
import model.Wallet;

public class BankWalletParser {
	
	private WalletFactory bankWalletFactory;

	/**
	 * The WalletParser parses the gotten wallet file content and creates
	 * wallet objects
	 */
	protected BankWalletParser() {
		bankWalletFactory = new BankWalletFactory();
	}

	/**
	 * The function parses gotten file content and returns the contract container
	 * which holds created contracts
	 * 
	 * @param fileAll = contract file content
	 * @param users   = user container which holds all users
	 * @param wallets = wallet container which holds all wallets of users
	 * @return Contract Container
	 * @throws FileFormatException 
	 * @throws JSONException
	 */
	protected IContainer<Wallet> parseWallets(String fileAll) throws FileFormatException {
		IContainer<Wallet> wallets = null;
		try {
			JSONObject jsonContracts;
			jsonContracts = (new JSONParser()).parse(fileAll); // get json object of file content
			wallets = parse(jsonContracts); // parse wallets
		} catch (JSONException e) {
		}

		return wallets;

	}
	


	private IContainer<Wallet> parse(JSONObject jsonObject)
			throws JSONException, FileFormatException {
		IContainer<Wallet> wallets = new WalletContainer();
		// iterate json object
		Iterator<?> keys = jsonObject.keys();
		Object val = null;
		while (keys.hasNext()) {
			Object keyTemp = keys.next();
			if (!(keyTemp instanceof String))
				throw new JSONException("WalletParser.parse::Key is not a string");
			// get the coin values and invoke createWallet()
			// to get created contract
			String key = (String) keyTemp;
			val = jsonObject.get(key);
			if (val instanceof JSONObject) {				
				JSONArray banknotes = (JSONArray) ((JSONObject) val).get("banknotes");		
				
				Dictionary<String,String> banknote = new Hashtable<String,String>();
				for(int i=0;i<banknotes.length();i++)
				{
					JSONObject coinJson = (JSONObject) banknotes.get(i);
					String coinId = coinJson.getString("id");
					String quantity = coinJson.getString("quantity");				
					banknote.put(coinId, quantity);
				}
				Wallet wallet = createWallet(key,banknote);
				wallets.add(wallet);
			}
		}
		return wallets;
	}
	
	private Wallet createWallet(String walletId, Dictionary<String,String> coins) throws FileFormatException
	{
		//!!WalletFactory Create
		Wallet createdWallet = bankWalletFactory.createWallet(walletId, coins);
		if(createdWallet != null)
		{
			return createdWallet;
		}
		else
		{
			throw new FileFormatException("Wrong Format for bank_wallets.json");
		}		
	}


	
	
	

}