package fileio.repository;

import model.User;
import model.Wallet;
import storage.IContainer;
import storage.*;
import model.Currency;

import exception.FileFormatException;
import fileio.FileIO;
import fileio.IFileIO;

/**
 * This class holds all informations
 *
 */
public class FileController {

	private IContainer<User> users;
	private IContainer<Wallet> cryptoWallets;
	private IContainer<Wallet> bankWallets;
	private IContainer<Currency> banknotes;
	private IContainer<Currency> coins;
	private IFileIO fileIO;

	protected FileController() {
		fileIO = new FileIO();
		users = new UserContainer();
		cryptoWallets = new WalletContainer();
		bankWallets = new WalletContainer();
		banknotes = new CurrencyContainer();
		coins = new CurrencyContainer();
	}

	/**
	 * This function reads all entities from files and assign the contents to the
	 * containers
	 * 
	 * @throws FileFormatException
	 */
	protected void readAll() throws FileFormatException {
		try {
			fileIO.readBanknotes("data\\banknotes.json");
			fileIO.readCoins("data\\coins.json");
			fileIO.readCryptoWallets("data\\crypto_wallets.json");
			fileIO.readBankWallets("data\\bank_wallets.json");
			fileIO.readUsers("data\\users.xml");
			
		} catch (Exception e) {
			throw new FileFormatException(e.getMessage());
		}
	}

	/**
	 * This function writes all entities to necessary files
	 * 
	 * @throws FileFormatException
	 */
	protected void writeAll() throws FileFormatException {
		try {
			fileIO.writeUsers(users, "data\\users.xml");
			fileIO.writeCryptoWallets(cryptoWallets, "data\\crypto_wallets.json");
			fileIO.writeBankWallets(bankWallets, "data\\bank_wallets.json");
			fileIO.writeCoins(coins,"data\\coins.json");
			fileIO.writeBanknotes(banknotes,"data\\banknotes.json");
		} catch (Exception e) {
			throw new FileFormatException(e.getMessage());
		}
	}


	protected IContainer<User> users() {
		return users;
	}

	protected IContainer<Wallet> crypto_wallets() {
		return cryptoWallets;
	}

	protected IContainer<Wallet> bank_wallets() {
		return bankWallets;
	}

	protected IContainer<Currency> coins(){
		return coins;
	}
	
	protected IContainer<Currency> banknotes(){
		return banknotes;
	}

}
