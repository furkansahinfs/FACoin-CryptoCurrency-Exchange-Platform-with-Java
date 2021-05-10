package fileio;

import model.User;
import model.Wallet;
import storage.IContainer;

/**
 * This class handles fileio operations
 */
public interface IFileIO {
	
	/**
	 * This function reads outfits
	 * 
	 * @param filePath of the crypto_wallets.json file
	 * @returns read crypto_wallets
	 * @throws Exception for fileio exceptions
	 */
	public IContainer<Wallet> readCryptoWallets(String filePath) throws Exception;
	
	/**
	 * This function reads outfits
	 * 
	 * @param filePath of the bank_wallets.json file
	 * @returns read bank_wallets
	 * @throws Exception for fileio exceptions
	 */
	public IContainer<Wallet> readBankWallets(String filePath) throws Exception;


	/**
	 * This function reads users
	 * 
	 * @param filePath of users file
	 * @returns read users
	 * @throws Exception for fileio exceptions
	 */
	public IContainer<User> readUsers(String filePath) throws Exception;


	/**
	 * This function writes users
	 * 
	 * @param users    of the system
	 * @param filePath of the file
	 * @throws Exception for write operations
	 */
	public void writeUsers(IContainer<User> users, String filePath) throws Exception;
	
	/**
	 * This function writes wallets
	 * 
	 * @param cryptoWallets    of the system
	 * @param filePath of the file
	 * @throws Exception for write operations
	 */
	public void writeCryptoWallets(IContainer<Wallet> cryptoWallets, String filePath) throws Exception;
	
	
	/**
	 * This function writes users
	 * 
	 * @param bankWallets    of the system
	 * @param filePath of the file
	 * @throws Exception for write operations
	 */
	public void writeBankWallets(IContainer<Wallet> bankWallets, String filePath) throws Exception;


}
