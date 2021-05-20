package fileio.repository;

import java.util.Iterator;
import exception.FileWriteException;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import storage.IContainer;
import model.Currency;

public class CoinRepository implements IRepository<Currency>,IRestrictedRepository<Currency> {

	public CoinRepository() {

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
		} catch (FileWriteException e) {
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
		final IContainer<Currency> currencies = BaseRepository.coins();
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
	
	public IContainer<Currency> coins()
	{
		return BaseRepository.coins();
	}
	
	public boolean addEntity(Currency currency)
	{
		return BaseRepository.coins().add(currency);
	}
	
	public Currency removeEntity(Currency currency)
	{
		try {
			return BaseRepository.coins().remove(currency);
		} catch (ItemNotFoundException e) {
			return null;
		}
	}

	@Override
	public DatabaseResult getByName(String name)  {
		
		final IContainer<Currency> currencies = BaseRepository.coins();
		String message = "";
		Object result = null;
		try {
			// try to find the user
			result = currencies.getByName(name);
		} catch (ItemNotFoundException | NotSupportedException e) {
			message += e.getMessage();
		}
		return new DatabaseResult(result, message); 
	}
	
	@Override
	public final Iterator<Currency> getAll() {
		return BaseRepository.coins().iterator();
	}
}
