package view;

import java.awt.Color;

public class LabelInfo {
	public Color color;
	public Double percent;
	public String coinName;
	public Double value;
	public String banknote;

	public LabelInfo(Color color, Double percent, String name, Double value, String banknote) {
		this.color = color;
		this.percent = percent;
		this.coinName = name;
		this.value = value;
		this.banknote = banknote;
	}

	public String toString() {
		return this.coinName + "/" + this.banknote + " : " + this.value + " (" + this.percent + "%)";
	}
}