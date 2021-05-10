package fileio.parser;

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

	public Parser() {
		this.userParser = new UserParser();
		this.cryptoWalletParser = new CryptoWalletParser();
		this.bankWalletParser = new BankWalletParser();
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
	 * The function parses gotten file content and returns the user container which
	 * holds created users
	 * 
	 * @param fileAll = crypto_wallets.json file content
	 * @return Wallet Container
	 * @throws XMLException
	 */
	public IContainer<Wallet> parseCryptoWallets(String fileAll)
			throws Exception {
		return cryptoWalletParser.parseWallets(fileAll);
	}
	
	/**
	 * The function parses gotten file content and returns the user container which
	 * holds created users
	 * 
	 * @param fileAll = bank_wallets.json file content
	 * @return Wallet Container
	 * @throws XMLException
	 */
	public IContainer<Wallet> parseBankWallets(String fileAll)
			throws Exception {
		return bankWalletParser.parseWallets(fileAll);
	}


}
