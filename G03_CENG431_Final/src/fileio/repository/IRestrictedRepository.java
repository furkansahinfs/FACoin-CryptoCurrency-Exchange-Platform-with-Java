package fileio.repository;

public interface IRestrictedRepository<T>{
	public DatabaseResult getById(String id);
	public DatabaseResult getByName(String name);
}