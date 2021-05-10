package fileio;

import exception.FileFormatException;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import storage.IContainer;
import model.Wallet;

public class WalletRepository {

	public WalletRepository() {

	}

	/**
	 * The function tries to write the wallets to the necessary file.
	 * 
	 * @return DatabaseResult(null, message);
	 */
	public DatabaseResult saveChanges() {
		String message = "";
		try {
			BaseRepository.saveChanges();
		} catch (FileFormatException e) {
			message += e.getMessage();
		}
		return new DatabaseResult(null, message);
	}

	/**
	 * The function tries to find the crypto wallet of given id and returns the database
	 * result. If user is found > database.result object = wallet, else
	 * database.result object = null
	 * 
	 * @param id = gotten wallet id
	 * @return database result
	 */
	public DatabaseResult getCryptoWalletById(String id) {
		// get user container of system which holds all users
		final IContainer<Wallet> cryptoWallets = BaseRepository.crypto_wallets();
		String message = "";
		Object result = null;
		try {
			// try to find the user
			result = cryptoWallets.getById(id);
		} catch (ItemNotFoundException | NotSupportedException e) {
			message += e.getMessage();
		}
		return new DatabaseResult(result, message);
	}
	
	/**
	 * The function tries to find the bank wallet of given id and returns the database
	 * result. If user is found > database.result object = wallet, else
	 * database.result object = null
	 * 
	 * @param id = gotten wallet id
	 * @return database result
	 */
	public DatabaseResult getBankWalletById(String id) {
		// get user container of system which holds all users
		final IContainer<Wallet> bankWallets = BaseRepository.bank_wallets();
		String message = "";
		Object result = null;
		try {
			// try to find the user
			result = bankWallets.getById(id);
		} catch (ItemNotFoundException | NotSupportedException e) {
			message += e.getMessage();
		}
		return new DatabaseResult(result, message);
	}

	/**
	 * The function returns the Crypto Wallets' container of system
	 * 
	 * @return Crypto Wallet Container which holds all bank wallets
	 */
	public final IContainer<Wallet> getCryptoWallets() {
		return BaseRepository.crypto_wallets();
	}
	
	/**
	 * The function returns the Bank Wallets' container of system
	 * 
	 * @return Bank Wallet Container which holds all bank wallets
	 */
	public final IContainer<Wallet> getBankWallets() {
		return BaseRepository.bank_wallets();
	}
}
