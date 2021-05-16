package app;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.ListModel;

import enums.ECoins;
import model.User;
import service.CoinPrintService;
import view.HomeView;

public class App {

	public static void main(String[] args) throws Exception {

		/*
		 * new Thread(() -> { // Do whatever }).start();
		 * 
		 * Runnable helloRunnable = new Runnable() { public void run() {
		 * System.out.println("Hello world"); } };
		 * 
		 * ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		 * executor.scheduleAtFixedRate(helloRunnable, 0, 5, TimeUnit.SECONDS);
		 */
		JFrame frame = new JFrame();
		frame.setContentPane(new HomeView());
		frame.setVisible(true);

	}
}
/*
	 * The function returns the selected coin.
	 * 
	 * @param String of selected user name
	
	public String getListSelected() {
		return coinList.getSelectedValue();
	}
	
	
	
	 * If a user is gotten in the update method, set the followed users' collections
	 * list according the gotten user's followed users
	 * 
	 * @param user
	
	private void updateScroll(User user) {
		final var listModel = (new CoinPrintService(user)).getScroolString();
		coinList.setModel((ListModel<String>) listModel);
	}*/
