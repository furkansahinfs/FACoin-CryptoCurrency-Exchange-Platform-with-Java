package view.decorator;

import view.IColorable;
import view.color.ColorPalette;
import view.color.DarkTheme;

public class LightThemeDecorator extends ThemeDecorator {

	public LightThemeDecorator(IColorable view) {
		super(view);
	}

	@Override
	public void set() {
		ColorPalette palette = new ColorPalette(new DarkTheme());
		this.view.setPalette(palette);
	}

}
