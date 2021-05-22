package configuration;

import controller.Consumable;
import controller.IConsumable;
import exception.FileReadException;
import fileio.repository.BaseRepository;
import httpio.repository.HttpRepository;
import mediator.LoginMediator;
import mediator.Updater;
import view.AppWindow;

public class ConfigurationManager {

	public ConfigurationManager() {

	}

	protected void configureUpdateThread() {
		Updater updater = new Updater();
		updater.RUN();
	}

	protected void configureAppWindow() {
		IConsumable window = new AppWindow();
		window.supressNotUsed();

	}

	protected void configureDatabases() throws FileReadException {
		BaseRepository br = new BaseRepository();
		br.initDatabase();
		HttpRepository httpRepo = new HttpRepository();
		httpRepo.initHttpDatabase();
		
	}

	protected void configureStart() {
		Consumable loginMediator = new LoginMediator();
		loginMediator.supressNotUsed();
	}
}
