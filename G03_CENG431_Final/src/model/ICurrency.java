package model;

import java.util.Dictionary;

public interface ICurrency{
	public Dictionary<String,Float> getValue();
	public Dictionary<String,Float> getOldValue();
}