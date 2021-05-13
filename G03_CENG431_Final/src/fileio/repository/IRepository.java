package fileio.repository;
public interface IRepository<T>{
	public boolean addEntity(T entity);
	public T removeEntity(T entity);
}