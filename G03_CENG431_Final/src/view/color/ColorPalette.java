package view.color;

import java.awt.Color;

public class ColorPalette {
	
	public final Color SECOND_COLOR;
	public final Color BACKGROUND;
	public final Color FIRST_COLOR;
	
	public ColorPalette(Theme theme) {
		this.SECOND_COLOR = theme.secondColor;
		this.FIRST_COLOR = theme.firstColor;
		this.BACKGROUND = theme.background;
	}
}
