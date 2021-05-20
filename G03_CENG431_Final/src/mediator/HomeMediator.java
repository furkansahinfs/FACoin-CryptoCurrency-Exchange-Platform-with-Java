package mediator;

import controller.HomeController;
import exception.HttpRequestException;
import model.User;
import view.HomeView;
import view.decorator.CoinListDecorator;
import view.decorator.DarkThemeDecorator;
import view.decorator.JListDecorator;
import view.decorator.ThemeDecorator;

public class HomeMediator {

	private User user;
	private HomeView view;
	private HomeController controller;
	

	public HomeMediator(User user) {
		view = new HomeView();
		JListDecorator decorator = new CoinListDecorator(view);
		UpdatePool.POOL.add(decorator);
		ThemeDecorator theme = new DarkThemeDecorator(view);
		controller = new HomeController(this);
	}

	public HomeView getView() {
		return view;
	}

	
	public void logout() {
		view.setVisible(false);
		UpdatePool.POOL.clear();
		LoginMediator mediator = new LoginMediator();
	}	

	public void getSelectedCoinView() throws HttpRequestException {

		String selectedItemName = view.getListSelected();
		view.setVisible(false);
		String[] splittedItem = selectedItemName.split(":");
		CoinInfoMediator mediator = new CoinInfoMediator(splittedItem[0]);
	
	}

}
