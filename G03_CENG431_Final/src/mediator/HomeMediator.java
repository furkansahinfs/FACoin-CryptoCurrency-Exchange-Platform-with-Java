package mediator;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import controller.HomeController;
import exception.HttpRequestException;
import fileio.repository.UpdateMediator;
import httpio.repository.HttpController;
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
	private UpdateMediator mediator;
	private JListDecorator decorator;
	private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	public HomeMediator(User user) {
		view = new HomeView();
		mediator = new UpdateMediator();
		decorator = new CoinListDecorator(view);
		ThemeDecorator theme = new DarkThemeDecorator(view);
		controller = new HomeController(this);
		runService();

	}

	public HomeView getView() {
		return view;
	}

	
	public void logout() {
		view.setVisible(false);
		LoginMediator mediator = new LoginMediator();
	}
	
	public void runService() {
		ScheduledExecutorService executor = null;
		try {
			Runnable helloRunnable = new Runnable() {
				public void run() {
					mediator.updateValues();
					decorator.setList();
				}
			};

			executor = Executors.newScheduledThreadPool(1);
			executor.scheduleAtFixedRate(helloRunnable, 0, 10, TimeUnit.SECONDS);
		} catch (Exception e) {
			executor.shutdown();
		}
	}
	
	

	public void getSelectedCoinView() throws HttpRequestException {

		String selectedItemName = view.getListSelected();
		view.setVisible(false);
		String[] splittedItem = selectedItemName.split(":");
		CoinInfoMediator mediator = new CoinInfoMediator(splittedItem[0]);
	
	}

}
