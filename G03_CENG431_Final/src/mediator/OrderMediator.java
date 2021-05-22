package mediator;

import java.awt.Color;
import javax.swing.JLabel;

import controller.Consumable;
import controller.IConsumable;
import controller.OrderController;
import model.User;
import service.OrderService;
import view.OrderView;
import view.color.ColorPalette;
import view.color.DarkTheme;
import view.decorator.DarkThemeDecorator;
import view.decorator.Decorator;
import view.decorator.OrderListDecorator;

public class OrderMediator extends Consumable {

	private final User user;
	private final OrderView view;
	private final IConsumable controller;
	private Color approvedColor;
	private Decorator listDecorator;
	private OrderService service;
	
	public OrderMediator(User user) {
		this.approvedColor = (new ColorPalette(new DarkTheme())).SECOND_COLOR;
		this.user = user;
		view = new OrderView(user.getId());
		new DarkThemeDecorator(view);
		listDecorator = new OrderListDecorator(view);
		UpdatePool.POOL.add(listDecorator);
		controller = new OrderController(this);
		service = new OrderService(user);
		
	}

	public void back() {
		controller.supressNotUsed();
		UpdatePool.POOL.remove(listDecorator);
		getView().setVisible(false);
		IConsumable mediator = new HomeMediator(user);
		mediator.supressNotUsed();
		
	}

	public void rejectTransaction() {
		String password = view.getPassword();
		view.hideReject();
		if(!password.equals(user.getPassword())){
			view.showAlert();
			return;
		}
		JLabel label = view.getListSelected();
		if(label==null)
			return;
		service.setRejectProcesses(label.getText(),listDecorator);
		UpdatePool.POOL.add(listDecorator);
	}

	public void rejectTransactionBridge() {
		JLabel label = view.getListSelected();
		if(label==null)
			return;
		if(label.getForeground().equals(approvedColor))
			return;
		UpdatePool.POOL.remove(listDecorator);
		view.showReject();
	}
	


	/**
	 * @return the view
	 */
	public OrderView getView() {
		return view;
	}


}
