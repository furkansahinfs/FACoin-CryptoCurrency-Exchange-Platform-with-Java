package model;

import java.util.Dictionary;

public class Coin extends Currency{

	public Coin(String id, Dictionary<String, Float> value, String name) {
		super(id, value, name);
	}
	
	public Coin(String id, String name) {
		super(id, name);
	}
	
	
}