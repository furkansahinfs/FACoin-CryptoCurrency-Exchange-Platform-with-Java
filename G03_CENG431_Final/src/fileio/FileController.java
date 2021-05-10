package fileio;

import model.User;
import model.Wallet;
import storage.IContainer;

import model.Currency;

import exception.FileFormatException;

/**
 * This class holds all informations
 *
 */
public class FileController {

	private IContainer<User> users;
	private IContainer<Wallet> cryptoWallets;
	private IContainer<Wallet> bankWallets;
	private IContainer<Currency> currencies;
	private IFileIO fileIO;

	protected FileController() {
		fileIO = new FileIO();
	}

	/**
	 * This function reads all entities from files and assign the contents to the
	 * containers
	 * 
	 * @throws FileFormatException
	 */
	protected void readAll() throws FileFormatException {
		try {
			cryptoWallets = fileIO.readCryptoWallets("data\\crypto_wallets.json");
			bankWallets = fileIO.readBankWallets("data\\bank_wallets.json");
			users = fileIO.readUsers("data\\users.xml");
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

	protected IContainer<Currency> currencies(){
		return currencies;
	}

}
