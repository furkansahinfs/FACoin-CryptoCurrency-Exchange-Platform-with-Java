package factory;
public abstract class AbstractFactory{
	public void createEntity(AbstractFactory factory){
		factory.createEntity(factory);
	}
}