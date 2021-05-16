package view.color;

import java.awt.Color;

public class DefaultTheme extends Theme{
	protected DefaultTheme() {
		super();
		DEFAULT_THEME();
	}


	private void DEFAULT_THEME() {
		this.background = Color.WHITE;
		this.firstColor = Color.BLACK;
		this.secondColor = Color.BLACK;
	}
}
