package view.decorator;

import view.AppView;

public abstract class ThemeDecorator extends Decorator{

	protected AppView view;
	
	public ThemeDecorator(AppView view){
		this.view = view;
		setPalette();
	}
	
	protected abstract void setPalette();
}
