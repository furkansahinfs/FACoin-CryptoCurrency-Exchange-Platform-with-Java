package factory;
public class AbstractFactory{
	
	private AbstractFactory factory;
	public AbstractFactory(AbstractFactory factory){
		this.factory = factory;
	}
	
	public Object createEntity(Object args){
		return factory.createEntity(args);
	}
}

