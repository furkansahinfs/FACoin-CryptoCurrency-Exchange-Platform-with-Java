package mediator;

import java.awt.Color;
import javax.swing.JLabel;
import controller.OrderController;
import model.User;
import service.OrderService;
import view.OrderView;
import view.color.ColorPalette;
import view.color.DarkTheme;
import view.decorator.DarkThemeDecorator;
import view.decorator.Decorator;
import view.decorator.OrderListDecorator;

public class OrderMediator {

	private final User user;
	private final OrderView view;
	private final OrderController controller;
	private Color approvedColor;
	private Decorator listDecorator;
	private OrderService service;
	
	public OrderMediator(User user) {
		this.approvedColor = (new ColorPalette(new DarkTheme())).SECOND_COLOR;
		this.user = user;
		view = new OrderView(user.getId());
		Decorator themeDecorator = new DarkThemeDecorator(view);
		listDecorator = new OrderListDecorator(view);
		UpdatePool.POOL.add(listDecorator);
		controller = new OrderController(this);
		service = new OrderService(user);
		
	}

	public void back() {
		UpdatePool.POOL.remove(listDecorator);
		getView().setVisible(false);
		HomeMediator mediator = new HomeMediator(user);
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
		service.setRejectProcesses(label.getText());
		Decorator listDecorator = new OrderListDecorator(view);
	}

	public void rejectTransactionBridge() {
		JLabel label = view.getListSelected();
		if(label==null)
			return;
		if(label.getForeground().equals(approvedColor))
			return;
		view.showReject();
	}
	


	/**
	 * @return the view
	 */
	public OrderView getView() {
		return view;
	}


}
