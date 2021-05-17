package model;

import java.util.Dictionary;
import java.util.Hashtable;

import storage.Name;

public abstract class Currency extends AbstractEntity implements ICurrency {

	private Dictionary<String,Float> value;
	private Dictionary<String,Float> old_value;
	public final String name;

	public Currency(String id, String name) {
		super(id);
		this.value = new Hashtable<String,Float>();
		this.old_value = new Hashtable<String,Float>();
		this.name = name;
	}
	
	public Currency(String id, Dictionary<String,Float> value, String name) {
		super(id);
		this.value = value;
		this.name = name;
		this.old_value = value;
	}

	public Dictionary<String,Float> getValue() {
		return this.value;
	}
	
	public Dictionary<String,Float> getOldValue() {
		return this.old_value;
	}

	public void setValue(Dictionary<String,Float> value) {
		this.value = value;
	}
	
	public Float addValue(String banknoteName, Float value)
	{

		return this.value.put(banknoteName, value);
	}
	
	public void addOldValue(String banknoteName, Float value){
		this.old_value.put(banknoteName, value);
	}
	
	public String getName() {
		return this.name;
	}

	public boolean equals(String id) {
		return this.getId().equals(id);
	}

	public boolean equals(Name Name) {
		return this.name.equals(Name.name);
	}

}
