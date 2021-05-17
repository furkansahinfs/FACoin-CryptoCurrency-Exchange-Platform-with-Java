package view.decorator;

import view.AppView;
import view.color.ColorPalette;
import view.color.DarkTheme;

public class LightThemeDecorator extends ThemeDecorator {

	public LightThemeDecorator(AppView view) {
		super(view);
	}

	@Override
	protected void setPalette() {
		ColorPalette palette = new ColorPalette(new DarkTheme());
		this.view.setPalette(palette);
	}

}
