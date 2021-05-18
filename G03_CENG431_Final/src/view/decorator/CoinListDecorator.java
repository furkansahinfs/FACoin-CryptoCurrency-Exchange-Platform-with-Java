package view.decorator;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;

import mediator.CoinListMediator;
import view.AppView;
import view.list.CoinList;


public class CoinListDecorator extends JListDecorator{

	public CoinListDecorator(AppView view) {
		super(view);
	}

	@Override
	public void setList() {
		CoinListMediator mediator = new CoinListMediator();
		DefaultListModel<JLabel> listModel = mediator.getList();
		view.setList(new CoinList(listModel));
	}

}
