package mediator;

import javax.swing.JLabel;
import controller.HomeController;
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
import view.decorator.ThemeDecorator;

public class HomeMediator {

	private User user;
	private HomeView view;
	private HomeController controller;
	private JListDecorator decorator;
	private boolean ascending = false;
	private boolean descending = false;
	private boolean fav = false;
	
	public HomeMediator(User user) {
		this.user = user;
		view = new HomeView(user.getId());
		decorator = new CoinListDecorator(view);
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
	
	public void goWalletPage() {
		view.setVisible(false);
		UpdatePool.POOL.clear();
		WalletMediator mediator = new WalletMediator(user);
	}	

	public void getSelectedCoinView() throws HttpRequestException {

		JLabel label = view.getListSelected();
		if(label==null)
		{
			return;
		}
		view.setVisible(false);		
		String[] splittedItem = label.getText().split(":");		
		CoinInfoMediator mediator = new CoinInfoMediator(splittedItem[0],this.user);
	
	}
	//TODO bura nasý olcak amuða jakam
	public void sort(ESort sortType) {
		Decorator sortDecorator = null;
		if(sortType.equals(ESort.ASCENDING) && !ascending) {
			UpdatePool.POOL.clear();
			sortDecorator = new AscendingOrderListDecorator(decorator);
			ascending = true;
			descending = false;
		}
		else if(sortType.equals(ESort.DESCENDING) && !descending) {
			UpdatePool.POOL.clear();
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
			UpdatePool.POOL.clear();
			decorator = new FavListDecorator(view);
			UpdatePool.POOL.add(decorator);
			fav = true;
		}
	}

	public void ordersView() {
		// TODO Auto-generated method stub
		
	}

}
