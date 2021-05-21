package fileio.repository;

import java.util.Iterator;
import exception.FileWriteException;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import storage.IContainer;
import model.Transaction;
import model.Wallet;

public class TransactionRepository implements IRepository<Transaction>,IRestrictedRepository<Transaction> {

	public TransactionRepository() {

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
		} catch (FileWriteException e) {
			message += e.getMessage();
		}
		return new DatabaseResult(message, message);
	}

	/**
	 * The function tries to find the crypto wallet of given id and returns the database
	 * result. If user is found > database.result object = wallet, else
	 * database.result object = null
	 * 
	 * @param id = gotten wallet id
	 * @return database result
	 */
	public DatabaseResult getById(String id) {
		// get user container of system which holds all users
		final IContainer<Transaction> transactions = BaseRepository.transactions();
		String message = "";
		Object result = null;
		try {
			// try to find the user
			result = transactions.getById(id);
			
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
	public final IContainer<Transaction> getTransactions() {
		return BaseRepository.transactions();
	}
	

	@Override
	public boolean addEntity(Transaction entity) {
		boolean result = BaseRepository.transactions().add(entity);
		return result;
	}

	@Override
	public Transaction removeEntity(Transaction entity) {
		try {
			return (Transaction) BaseRepository.transactions().remove(entity);
		} catch (ItemNotFoundException e) {
			return null;
		}
	}

	@Override
	public DatabaseResult getByName(String name) {
		return null;
	}

	@Override
	public final Iterator<Transaction> getAll() {
		return null;
	}

	@Override
	public boolean isEmpty() {
		return BaseRepository.transactions().isEmpty();
	}

	
	

}
