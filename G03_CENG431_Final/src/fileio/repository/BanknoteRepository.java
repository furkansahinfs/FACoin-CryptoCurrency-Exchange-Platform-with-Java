package fileio.repository;

import exception.FileFormatException;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import storage.IContainer;
import model.Currency;

public class BanknoteRepository implements IRepository<Currency>,IRestrictedRepository<Currency> {

	public BanknoteRepository() {

	}

	/**
	 * The function tries to write the outfits to the necessary file.
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
	 * The function tries to find the user of given name and returns the database
	 * result. If user is found > database.result object = user, else
	 * database.result object = null
	 * 
	 * @param name = gotten user name
	 * @return database result
	 */
	public DatabaseResult getById(String id) {
		// get user container of system which holds all users
		final IContainer<Currency> currencies = BaseRepository.banknotes();
		String message = "";
		Object result = null;
		try {
			// try to find the user
			result = currencies.getById(id);
		} catch (ItemNotFoundException | NotSupportedException e) {
			message += e.getMessage();
		}
		return new DatabaseResult(result, message);
	}
	
	public IContainer<Currency> banknotes()
	{
		return BaseRepository.banknotes();
	}
	
	public boolean addEntity(Currency currency)
	{
		return BaseRepository.banknotes().add(currency);
	}
	
	public Currency removeEntity(Currency currency)
	{
		try {
			return BaseRepository.banknotes().remove(currency);
		} catch (ItemNotFoundException e) {
			return null;
		}
	}

	@Override
	public DatabaseResult getByName(String name) {
		return null;
	}
	
}
