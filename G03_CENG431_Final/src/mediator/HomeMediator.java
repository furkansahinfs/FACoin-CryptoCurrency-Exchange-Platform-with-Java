package mediator;

import javax.swing.JLabel;

import controller.Consumable;
import controller.HomeController;
import controller.IConsumable;
import enums.ESort;
import exception.HttpRequestException;
import model.User;
import view.HomeView;
import view.decorator.AscendingOrderListDecorator;
import view.decorator.CoinListDecorator;
import view.decorator.DarkThemeDecorator;
import view.decorator.Decorator;
import view.decorator.DescendingOrderListDecorator;
import view.decorator.FavListDecorator;
import view.decorator.JListDecorator;

public class HomeMediator extends Consumable{

	private User user;
	private HomeView view;
	private IConsumable controller;
	private JListDecorator decorator;
	private boolean ascending = false;
	private boolean descending = false;
	private boolean fav = false;
	
	public HomeMediator(User user) {
		this.user = user;
		view = new HomeView(user.getId());
		decorator = new CoinListDecorator(view);
		UpdatePool.POOL.add(decorator);
		UpdatePool.POOL.add(new TransactionMediator(user));
		new DarkThemeDecorator(view);
		controller = new HomeController(this);
	}

	public HomeView getView() {
		return view;
	}

	
	public void logout() {
		controller.supressNotUsed();
		view.setVisible(false);
		UpdatePool.POOL.remove(decorator);
		IConsumable mediator = new LoginMediator();
		mediator.supressNotUsed();
	}	
	
	public void goWalletPage() {
		view.setVisible(false);
		UpdatePool.POOL.remove(decorator);
		IConsumable mediator = new WalletMediator(user);
		mediator.supressNotUsed();
	}	

	public void getSelectedCoinView() throws HttpRequestException {

		JLabel label = view.getListSelected();
		if(label==null)
		{
			return;
		}
		view.setVisible(false);		
		String[] splittedItem = label.getText().split(":");		
		IConsumable mediator = new CoinInfoMediator(splittedItem[0],this.user);
		mediator.supressNotUsed();
	
	}
	
	public void sort(ESort sortType) {
		Decorator sortDecorator = null;
		if(sortType.equals(ESort.ASCENDING) && !ascending) {
			UpdatePool.POOL.remove(decorator);
			sortDecorator = new AscendingOrderListDecorator(decorator);
			ascending = true;
			descending = false;
		}
		else if(sortType.equals(ESort.DESCENDING) && !descending) {
			UpdatePool.POOL.remove(decorator);
			sortDecorator = new DescendingOrderListDecorator(decorator);
			ascending = false;
			descending = true;
		}
		if(sortDecorator!=null) {
			UpdatePool.POOL.add(sortDecorator);
		}
		
	}

	public void favListView() {
		if(!fav) {
			UpdatePool.POOL.remove(decorator);
			decorator = new FavListDecorator(view);
			UpdatePool.POOL.add(decorator);
			fav = true;
		}
		else {
			UpdatePool.POOL.remove(decorator);
			decorator = new CoinListDecorator(view);
			UpdatePool.POOL.add(decorator);
			fav = false;
		}
	}

	public void ordersView() {
		view.setVisible(false);
		UpdatePool.POOL.remove(decorator);
		IConsumable mediator = new OrderMediator(user);
		mediator.supressNotUsed();
	}
}
