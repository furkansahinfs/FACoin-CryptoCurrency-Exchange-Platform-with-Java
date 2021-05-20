package view.decorator;

import view.AppView;
import view.list.List;

public abstract class JListDecorator extends Decorator{

	protected AppView view;
	protected List list;
	public JListDecorator(AppView view){
		this.view = view;
		set();
	}
	
	public abstract void update();
	
	public List getList() {
		return this.list;
	}
}
