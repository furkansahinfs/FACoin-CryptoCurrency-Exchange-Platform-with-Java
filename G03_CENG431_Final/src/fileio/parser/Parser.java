package fileio.parser;

import model.Currency;
import model.User;
import model.Wallet;
import storage.IContainer;

/**
 * This function is a middleware for parser classes
 *
 */
public class Parser {
	private UserParser userParser;
	private CryptoWalletParser cryptoWalletParser;
	private BankWalletParser bankWalletParser;
	private CurrencyParser currencyParser;

	public Parser() {
		this.userParser = new UserParser();
		this.cryptoWalletParser = new CryptoWalletParser();
		this.bankWalletParser = new BankWalletParser();
		this.currencyParser = new CurrencyParser();
	}

	/**
	 * The function parses gotten file content and returns the user container which
	 * holds created users
	 * 
	 * @param fileAll = users.xml file content
	 * @return User Container
	 * @throws XMLException
	 */
	public IContainer<User> parseUsers(String fileAll)
			throws Exception {
		return userParser.parseUsers(fileAll);
	}
	
	/**
	 * The function parses gotten file content and returns the crypto wallet container which
	 * holds created crypto wallets
	 * 
	 * @param fileAll = crypto_wallets.json file content
	 * @return Wallet Container
	 * @throws JSONException
	 */
	public IContainer<Wallet> parseCryptoWallets(String fileAll)
			throws Exception {
		return cryptoWalletParser.parseWallets(fileAll);
	}
	
	/**
	 * The function parses gotten file content and returns the bank wallet container which
	 * holds created bank wallets
	 * 
	 * @param fileAll = bank_wallets.json file content
	 * @return Wallet Container
	 * @throws JSONException
	 */
	public IContainer<Wallet> parseBankWallets(String fileAll)
			throws Exception {
		return bankWalletParser.parseWallets(fileAll);
	}
	
	
	/**
	 * The function parses gotten file content and returns the currency container which
	 * holds created currencies
	 * 
	 * @param fileAll = currencies.json file content
	 * @return Currency Container
	 * @throws JSONException
	 */
	public IContainer<Currency> parseCurrencies(String fileAll)
			throws Exception {
		return currencyParser.parseCurrencies(fileAll);
	}


}
