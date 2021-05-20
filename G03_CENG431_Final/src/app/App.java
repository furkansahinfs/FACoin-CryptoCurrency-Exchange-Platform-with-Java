package app;

import fileio.repository.BaseRepository;
import httpio.repository.HttpRepository;
import view.AppWindow;
import mediator.LoginMediator;
import mediator.Updater;

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
		HttpRepository httpRepo = new HttpRepository();
		Updater updater = new Updater();
		updater.RUN();
		AppWindow window = new AppWindow();
		//UpdateMediator updateMediator = new UpdateMediator();
		LoginMediator loginMediator = new LoginMediator();
		br.saveChanges();

		//CoinInfoView view = new CoinInfoView("CoinInfo");
		//view.pack();
		//view.setVisible(true);
		
 
// TODO wallet toplam
// TODO al sat, favlara alma, favdan silme, 

	}
}
