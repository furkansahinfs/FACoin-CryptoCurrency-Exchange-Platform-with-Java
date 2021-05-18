package view;

import java.awt.Color;

public class LabelInfo {
	public Color color;
	public Float percent;
	public String coinName;
	public Float value;
	public String banknote;

	public LabelInfo(Color color, Float percent, String name, Float value, String banknote) {
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