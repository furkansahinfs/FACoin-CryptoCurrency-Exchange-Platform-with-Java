package model;
public abstract class AbstractEntity{
	private final int id;
	
	public AbstractEntity(){
		this.id = 0;
	}
	
	public AbstractEntity(int id){
		this.id = id;
	}
}