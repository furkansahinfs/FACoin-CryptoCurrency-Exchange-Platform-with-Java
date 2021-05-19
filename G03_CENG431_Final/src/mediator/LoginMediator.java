package mediator;

import view.LoginView;
import view.decorator.DarkThemeDecorator;
import view.decorator.ThemeDecorator;
import controller.LoginController;
import model.User;
import service.AuthService;

public class LoginMediator {

	private AuthService service;
	private LoginView view;
	private LoginController controller;

	public LoginMediator() {
		service = new AuthService();
		view = new LoginView();
		ThemeDecorator theme = new DarkThemeDecorator(view);
		controller = new LoginController(this);
	}

	public void login() {
		User user = service.login(view.getUserName().getText(), view.getPassword());
		if (user == null) {
			view.printMessage(false); // this function is for printing credentials wrong message
		} else { // if true means not print the message
			view.printMessage(true);
			HomeMediator homeMediator = new HomeMediator(user);
			view.setVisible(false);

		}
	}

	public LoginView getView() {
		return view;
	}
}
