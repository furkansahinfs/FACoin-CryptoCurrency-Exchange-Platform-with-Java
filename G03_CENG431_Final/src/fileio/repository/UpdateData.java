package fileio.repository;

import java.util.Dictionary;
import java.util.Hashtable;

public class UpdateData {
	
	public String name;
	public Dictionary<String, Float> values;
	public UpdateData(String name){
		this.name = name;
		values = new Hashtable<String,Float>();
	}
	
	public void addKeyValue(String name, Float value){
		this.values.put(name, value);
	}
	
	protected  Dictionary<String, Float> getValues(){
		return this.values;
	}
}
