package configuration;

import exception.FileReadException;

public class Startup {

	private final ConfigurationManager manager;
	
	public Startup() {
		manager = new ConfigurationManager();
	}
	
	public void START() {
		try {
			manager.configureDatabases();
		} catch (FileReadException e) {
			return;
		}
		manager.configureAppWindow();
		manager.configureUpdateThread();
		manager.configureStart();
	}
}
