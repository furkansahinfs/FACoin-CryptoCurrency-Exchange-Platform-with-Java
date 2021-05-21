package mediator;

import controller.OrderController;
import model.User;
import view.OrderView;
import view.decorator.DarkThemeDecorator;
import view.decorator.Decorator;
import view.decorator.OrderListDecorator;

public class OrderMediator {

	private final User user;
	private final OrderView view;
	private final OrderController controller;
	
	public OrderMediator(User user) {
		this.user = user;
		view = new OrderView(user.getId());
		Decorator themeDecorator = new DarkThemeDecorator(getView());
		Decorator listDecorator = new OrderListDecorator(getView());
		controller = new OrderController(this);
	}

	public void back() {
		getView().setVisible(false);
		HomeMediator mediator = new HomeMediator(user);
	}

	public void rejectTransaction() {
		// TODO Auto-generated method stub
		
	}

	public void rejectTransactionBridge() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return the view
	 */
	public OrderView getView() {
		return view;
	}
}
