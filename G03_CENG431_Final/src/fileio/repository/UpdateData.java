package fileio.repository;

import java.util.Dictionary;
import java.util.Hashtable;

public class UpdateData {
	
	public String name;
	public Dictionary<String, Double> values;
	public UpdateData(String name){
		this.name = name;
		values = new Hashtable<String,Double>();
	}
	
	public void addKeyValue(String name, Double value){
		this.values.put(name, value);
	}
	
	protected  Dictionary<String, Double> getValues(){
		return this.values;
	}
}
