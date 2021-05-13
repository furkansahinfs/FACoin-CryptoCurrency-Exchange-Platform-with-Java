package fileio;

import model.Currency;
import model.User;
import model.Wallet;
import storage.IContainer;

/**
 * This class handles fileio operations
 */
public interface IFileIO {
	
	/**
	 * This function reads crpyto wallets
	 * 
	 * @param filePath of the crypto_wallets.json file
	 * @returns read crypto_wallets
	 * @throws Exception for fileio exceptions
	 */
	public void readCryptoWallets(String filePath) throws Exception;
	
	/**
	 * This function reads bank wallets
	 * 
	 * @param filePath of the bank_wallets.json file
	 * @returns read bank_wallets
	 * @throws Exception for fileio exceptions
	 */
	public void readBankWallets(String filePath) throws Exception;


	/**
	 * This function reads users
	 * 
	 * @param filePath of users file
	 * @returns read users
	 * @throws Exception for fileio exceptions
	 */
	public void readUsers(String filePath) throws Exception;


	/**
	 * This function reads coins
	 * 
	 * @param filePath of coins file
	 * @returns read coins
	 * @throws Exception for fileio exceptions
	 */
	public void readCoins(String filePath) throws Exception;
	
	
	/**
	 * This function reads banknotes
	 * 
	 * @param filePath of banknotes file
	 * @returns read banknotes
	 * @throws Exception for fileio exceptions
	 */
	public void readBanknotes(String filePath) throws Exception;

	


	/**
	 * This function writes users
	 * 
	 * @param users    of the system
	 * @param filePath of the file
	 * @throws Exception for write operations
	 */
	public void writeUsers(IContainer<User> users, String filePath) throws Exception;
	
	/**
	 * This function writes crypto wallets
	 * 
	 * @param cryptoWallets    of the system
	 * @param filePath of the file
	 * @throws Exception for write operations
	 */
	public void writeCryptoWallets(IContainer<Wallet> cryptoWallets, String filePath) throws Exception;
	
	
	/**
	 * This function writes bank wallets
	 * 
	 * @param bankWallets    of the system
	 * @param filePath of the file
	 * @throws Exception for write operations
	 */
	public void writeBankWallets(IContainer<Wallet> bankWallets, String filePath) throws Exception;
	
	/**
	 * This function writes coins
	 * 
	 * @param coins    of the system
	 * @param filePath of the file
	 * @throws Exception for write operations
	 */
	public void writeCoins(IContainer<Currency> coins, String filePath) throws Exception;
	
		/**
	 * This function writes users
	 * 
	 * @param banknotes    of the system
	 * @param filePath of the file
	 * @throws Exception for write operations
	 */
	public void writeBanknotes(IContainer<Currency> banknotes, String filePath) throws Exception;


}
