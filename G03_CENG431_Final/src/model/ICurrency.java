package model;

import java.util.Dictionary;

public interface ICurrency{
	public Dictionary<String,Double> getValue();
	public Dictionary<String,Double> getOldValue();
}