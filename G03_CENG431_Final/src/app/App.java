package app;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.ListModel;

import enums.ECoins;
import fileio.repository.BaseRepository;
import fileio.repository.UpdateMediator;
import model.User;
import service.CoinPrintService;
import view.HomeView;
import view.decorator.CoinListDecorator;
import view.decorator.JListDecorator;

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
		BaseRepository br = new BaseRepository();
		br.initDatabase();
		UpdateMediator updateMediator = new UpdateMediator();
		
		JFrame frame = new JFrame();
		HomeView view = new HomeView();
		frame.setContentPane(view);
		JListDecorator decorator = new CoinListDecorator(view);
		Runnable helloRunnable = new Runnable() {
			public void run() {
				updateMediator.updateValues();
				decorator.setList();				
			}
		};

		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(helloRunnable, 0, 10, TimeUnit.SECONDS);

		

		frame.setVisible(true);

	}
}
