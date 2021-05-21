package app;

import fileio.repository.BaseRepository;
import httpio.repository.HttpRepository;
import view.AppWindow;
import mediator.LoginMediator;
import mediator.Updater;

public class App {

	public static void main(String[] args) throws Exception {

		BaseRepository br = new BaseRepository();
		br.initDatabase();
		HttpRepository httpRepo = new HttpRepository();
		Updater updater = new Updater();
		updater.RUN();
		AppWindow window = new AppWindow();
		LoginMediator loginMediator = new LoginMediator();
		BaseRepository.saveChanges();
 
// TODO wallet toplam
// TODO al sat, favlara alma, favdan silme, 

	}
}
