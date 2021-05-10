package model;

public abstract class Currency extends AbstractEntity implements ICurrency{

	private float value;
	public final String name;

	public Currency(String id, float value, String name){
		super(id);
		this.value = value;
		this.name = name;
	}
	
	public float getValue(){
		return this.value;
	}
	
	public void setValue(float value){
		this.value = value;
	}

}
