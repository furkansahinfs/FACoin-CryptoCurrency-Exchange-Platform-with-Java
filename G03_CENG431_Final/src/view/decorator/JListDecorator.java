package view.decorator;

import view.AppView;

public abstract class JListDecorator extends Decorator{

	protected AppView view;
	
	public JListDecorator(AppView view){
		this.view = view;
		set();
	}
}
