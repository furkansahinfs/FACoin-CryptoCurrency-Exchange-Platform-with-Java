package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import settings.AppSettings;

public class Frame extends JFrame {

	private static final long serialVersionUID = -8368745851825149744L;
	
	/**
	 * It is the constructor of main frame
	 * @param name
	 */
	public Frame(String name){
		super(name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUpScreenBounds();
		setBounds(AppSettings.SCREEN_X, AppSettings.SCREEN_Y, AppSettings.WIDTH, AppSettings.HEIGHT);
	}

	private void setUpScreenBounds() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		
		AppSettings.SCREEN_X = (int) ((width/2)-(AppSettings.WIDTH/2));
		AppSettings.SCREEN_Y = (int) ((height/2)-(AppSettings.HEIGHT/2));
		
	}
	

}
