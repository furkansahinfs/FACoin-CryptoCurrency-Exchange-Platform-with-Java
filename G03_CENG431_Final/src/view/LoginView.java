package view;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import view.color.ColorPalette;
import view.list.List;


/**
 * The login view is a view which user can log in the system.
 *
 */
public class LoginView extends AppView  {


	private static final long serialVersionUID = 7766903621224003360L;
	private JButton loginButton; // login button
	private JPasswordField password; // password field
	private JTextField userName; // user name field
	private JLabel message; // warning message according to the situation
	private ColorPalette palette;
	
	public LoginView() {
		// set the observable and view elements in the view.
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		loginButton = new JButton("Sign in");
		loginButton.setBounds(300, 200, 100, 30);
		add(loginButton);
		
		password = new JPasswordField("Password");
		password.setBounds(300, 150, 100, 30);
		add(password);
		
		userName = new JTextField("Username");
		userName.setBounds(300, 100, 100, 30);
		add(userName);
		
		message = new JLabel("Incorrect login, please try again");
		message.setBounds(275, 250, 200, 30);
		add(message);
		message.setVisible(false);
		AppWindow.FRAME.getContentPane().add(this);
		AppWindow.FRAME.setVisible(true);
		

	}
	
	
	
	
	/**
	 * The function helps for detecting of clicking login button using listener
	 * 
	 * @param listener
	 */
	public void addLoginListener(ActionListener loginListener) {
		loginButton.addActionListener(loginListener);
	}
	
	/**
	 * The function helps for detecting of change in the
	 * user name field using listener
	 * 
	 * @param listener
	 */
	public void addTextListener(MouseListener textListener) {
		userName.addMouseListener(textListener);
	}
	
	/**
	 * The function returns the filled password
	 * 
	 * @return String = password text
	 */
	public char[] getPassword() {
		return password.getPassword();
	}

	/**
	 * The function returns the filled user name
	 * 
	 * @return String = user name text
	 */
	public JTextField getUserName() {
		return userName;
	}

	/**
	 * If user is not authenticated, show the warning message
	 * @param isAuthenticated
	 */
	public void printMessage(boolean isAuthenticated) {
		if (isAuthenticated)
			setVisible(false);
		else
			message.setVisible(true);
	}


	public void update(Object args) {
		if (args == null){
			password.setText("");
			userName.setText("");
			message.setVisible(false);
			this.setVisible(true);}
	}




	@Override
	public void setPalette(ColorPalette palette) {
		this.palette = palette;
		updateColors();
	}
	
	private void updateColors() {
		setBackground(palette.BACKGROUND);
		loginButton.setBackground(palette.FIRST_COLOR);
		loginButton.setForeground(palette.BACKGROUND);
		message.setForeground(palette.SECOND_COLOR);
		userName.setBackground(palette.BACKGROUND);
		userName.setForeground(palette.FIRST_COLOR);
		password.setBackground(palette.BACKGROUND);
		password.setForeground(palette.FIRST_COLOR);
	}




	@Override
	public void setList(List list) {
		// TODO Auto-generated method stub
		
	}

}