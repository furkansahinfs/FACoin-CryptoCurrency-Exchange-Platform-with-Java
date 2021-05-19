package view.decorator;

import view.IColorable;

public abstract class ThemeDecorator extends Decorator{

	protected IColorable view;
	
	public ThemeDecorator(IColorable view){
		this.view = view;
		setPalette();
	}
	
	protected abstract void setPalette();
}
