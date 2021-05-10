package model;
public abstract class AbstractEntity{
	private final String id;
	
	public AbstractEntity(){
		this.id = "";
	}
	
	public AbstractEntity(String id){
		this.id = id;
	}
	
	public String getId(){
		return this.id;
	}
}