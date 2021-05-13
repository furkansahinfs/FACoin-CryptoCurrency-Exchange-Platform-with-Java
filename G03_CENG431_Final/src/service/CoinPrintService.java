package service;

import javax.swing.DefaultListModel;

import fileio.repository.UserRepository;
import model.User;

/**
 * This class handles requests that coming from controller
 */
public class CoinPrintService {

	private final UserRepository up;
	private final User user;

	public CoinPrintService(User model) {
		this.user = model;
		this.up = new UserRepository();
	}

	/**
	 * This function renders scroll string of the home screen
	 * 
	 * @return rendered list
	 */
	public DefaultListModel<String> getScroolString() {
		
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		listModel.addElement("asd");

		return listModel;
	}
}
