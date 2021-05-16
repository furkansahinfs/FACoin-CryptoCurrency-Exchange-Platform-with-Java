package view;

import javax.swing.JPanel;

import view.color.ColorPalette;
import view.color.DarkTheme;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JList;
import java.awt.Color;

public class HomeView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1948466354580780395L;

	/**
	 * Create the panel.
	 */
	public HomeView() {
		ColorPalette palette = new ColorPalette(new DarkTheme());
		setBackground(palette.BACKGROUND);
		setLayout(null);
		
		JButton btnNewButton = new JButton("Logout");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton.setBackground(palette.FIRST_COLOR);
		btnNewButton.setForeground(palette.BACKGROUND);
		btnNewButton.setBounds(628, 11, 82, 23);
		add(btnNewButton);
		
		JList list = new JList();
		list.setBackground(palette.BACKGROUND);
		list.setBounds(174, 59, 363, 350);
		add(list);

	}
}
