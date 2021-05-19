package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import settings.AppSettings;

public class Frame extends JFrame {

	private static final long serialVersionUID = -8368745851825149744L;
	private int X = 0;
	private int Y = 0;
	
	/**
	 * It is the constructor of main frame
	 * @param name
	 */
	public Frame(String name){
		super(name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUpScreenBounds();
		setBounds(X, Y, AppSettings.WIDTH, AppSettings.HEIGHT);
	}

	private void setUpScreenBounds() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		
		X = (int) ((width/2)-(AppSettings.WIDTH/2));
		Y = (int) ((height/2)-(AppSettings.HEIGHT/2));
		
	}
	

}
