package app;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import fileio.repository.BaseRepository;
import fileio.repository.UpdateMediator;
import httpio.repository.HttpController;
import httpio.repository.HttpRepository;
import view.CoinInfoView;
import view.HomeView;
import view.decorator.CoinListDecorator;
import view.decorator.DarkThemeDecorator;
import view.decorator.JListDecorator;
import view.decorator.ThemeDecorator;

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
		
	/*ScheduledExecutorService executor = null;
		try{
		BaseRepository br = new BaseRepository();
		br.initDatabase();
		UpdateMediator updateMediator = new UpdateMediator();
		
		JFrame frame = new JFrame();
		HomeView view = new HomeView();
		frame.setContentPane(view);
		JListDecorator decorator = new CoinListDecorator(view);
		ThemeDecorator theme = new DarkThemeDecorator(view);
		Runnable helloRunnable = new Runnable() {
			public void run() {
				updateMediator.updateValues();
				decorator.setList();				
			}
		};
		frame.setVisible(true);
		executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(helloRunnable, 0, 10, TimeUnit.SECONDS);
		}catch(Exception e){
			executor.shutdown();	
		}*/
		BaseRepository br = new BaseRepository();
		br.initDatabase();
	
		HttpController httpController = new HttpController();
		HttpRepository httpRepo = new HttpRepository();
		httpController.readDayCandles("BTC","USD");
		JFrame frame = new JFrame();
		CoinInfoView view = new CoinInfoView("CoinInfo");
		view.pack();
		view.setVisible(true);
		
		
// TODO toString farklı klasslara ayır, file Write classı uzucak 
// TODO wallet ekranı, anlık değeri, adedi, değeri, wallet toplam
		

	}
}
