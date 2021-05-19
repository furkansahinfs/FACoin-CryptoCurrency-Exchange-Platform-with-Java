package fileio.parser;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.xml.sax.SAXException;

import exception.FileReadException;

/**
 * This function is a middleware for parser classes
 *
 */
public class Parser {
	private UserParser userParser;
	private CryptoWalletParser cryptoWalletParser;
	private BankWalletParser bankWalletParser;
	private CoinParser coinParser;
	private BanknoteParser banknoteParser;

	public Parser() {
		this.userParser = new UserParser();
		this.cryptoWalletParser = new CryptoWalletParser();
		this.bankWalletParser = new BankWalletParser();
		this.coinParser = new CoinParser();
		this.banknoteParser = new BanknoteParser();
	}

	/**
	 * The function parses gotten file content and returns the user container which
	 * holds created users
	 * 
	 * @param fileAll = users.xml file content
	 * @return User Container
	 * @throws FileReadException 
	 * @throws Exception 
	 * @throws XMLException
	 */
	public void parseUsers(String fileAll) throws FileReadException 
			  {
		try {
			userParser.parseUsers(fileAll);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			throw new FileReadException(e.getMessage());
		}
	}
	
	/**
	 * The function parses gotten file content and returns the crypto wallet container which
	 * holds created crypto wallets
	 * 
	 * @param fileAll = crypto_wallets.json file content
	 * @return Wallet Container
	 * @throws FileReadException 
	 * @throws JSONException
	 */
	public void parseCryptoWallets(String fileAll) throws FileReadException
			 {
		try {
			cryptoWalletParser.parseWallets(fileAll);
		} catch (JSONException e) {
			throw new FileReadException(e.getMessage());
		}
	}
	
	/**
	 * The function parses gotten file content and returns the bank wallet container which
	 * holds created bank wallets
	 * 
	 * @param fileAll = bank_wallets.json file content
	 * @return Wallet Container
	 * @throws FileReadException 
	 * @throws JSONException
	 */
	public void parseBankWallets(String fileAll) throws FileReadException
			 {
		try {
			bankWalletParser.parseWallets(fileAll);
		} catch (JSONException e) {
			throw new FileReadException(e.getMessage());
		}
	}
	
	
	/**
	 * The function parses gotten file content and returns the currency container which
	 * holds created currencies
	 * 
	 * @param fileAll = currencies.json file content
	 * @return Currency Container
	 * @throws FileReadException 
	 * @throws JSONException
	 */
	public void parseCoins(String fileAll) throws FileReadException
			 {
		try {
			coinParser.parseCurrencies(fileAll);
		} catch (JSONException e) {
			throw new FileReadException(e.getMessage());
		}
	}
	
	/**
	 * The function parses gotten file content and returns the currency container which
	 * holds created currencies
	 * 
	 * @param fileAll = currencies.json file content
	 * @return Currency Container
	 * @throws FileReadException 
	 * @throws JSONException
	 */
	public void parseBanknotes(String fileAll) throws FileReadException
			 {
		try {
			banknoteParser.parseCurrencies(fileAll);
		} catch (JSONException e) {
			throw new FileReadException(e.getMessage());
		}
	}


}
