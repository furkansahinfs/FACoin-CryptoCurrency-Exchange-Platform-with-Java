package fileio.repository;

import java.util.Iterator;

public interface IRestrictedRepository<T>{
	public DatabaseResult getById(String id);
	public DatabaseResult getByName(String name);
	public Iterator<T> getAll();
	public DatabaseResult saveChanges();
	public boolean isEmpty();
}