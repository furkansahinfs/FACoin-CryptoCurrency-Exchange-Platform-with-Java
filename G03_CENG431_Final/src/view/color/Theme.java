package view.color;

import java.awt.Color;

public abstract class Theme {
	
	protected Color secondColor;
	protected Color background;
	protected Color firstColor;
	
	public Theme(Color back, Color first, Color second) {
		this.secondColor = second;
		this.firstColor = first;
		this.background = back;
	}
	
	protected Theme() {
		SetDefaultTheme();
	}	
	
	private void SetDefaultTheme(){
		Theme theme = new DefaultTheme();
		this.secondColor = theme.secondColor;
		this.background = theme.background;
		this.firstColor = theme.firstColor;
	}
}
