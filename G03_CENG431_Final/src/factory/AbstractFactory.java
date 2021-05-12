package factory;
public class AbstractFactory{
	
	public AbstractFactory(){}
	
	public Object createEntity(AbstractFactory factory, Object args){
		return factory.createEntity(factory,args);
	}
}