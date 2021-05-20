package view.decorator;

public abstract class SortListDecorator extends Decorator{

	protected JListDecorator decorator;
	public SortListDecorator(JListDecorator decorator) {
		this.decorator = decorator;
		set();
	}
}
