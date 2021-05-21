package view.decorator;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import mediator.OrderListMediator;
import view.AppView;
import view.OrderView;
import view.list.OrderList;


public class OrderListDecorator extends JListDecorator{

	public OrderListDecorator(AppView view) {
		super(view);
	}

	@Override
	public void set() {
		update();
		view.setList(this.list);
	}

	@Override
	public void update() {
		String userId = ((OrderView) this.view).getUserId();
		OrderListMediator mediator = new OrderListMediator(userId);
		DefaultListModel<JLabel> listModel = mediator.getList();
		this.list = new OrderList(listModel);
	}

}
