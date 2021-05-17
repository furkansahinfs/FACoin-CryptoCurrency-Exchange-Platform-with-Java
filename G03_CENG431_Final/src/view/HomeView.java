package view;


import view.color.ColorPalette;
import view.list.CoinList;
import view.list.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;



public class HomeView extends AppView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1948466354580780395L;

	/**
	 * Create the panel.
	 */
	private List coinList;
	private ColorPalette palette;

	public HomeView() {

		setLayout(null);
		//new FRAME(new HomeView(new CoinList()))
		
		JButton btnNewButton = new JButton("Logout");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 12));
		//btnNewButton.setBackground(palette.FIRST_COLOR);
		//btnNewButton.setForeground(palette.BACKGROUND);
		btnNewButton.setBounds(628, 11, 82, 23);
		add(btnNewButton);
		
		//add(coinList);
	
		
		

	}

	/**
	 * The function returns the selected coin.
	 * 
	 * @param String of selected user name
	 */
	public String getListSelected() {
		return coinList.getSelectedValue().getText();
	}

	

	@Override
	public void setPalette(ColorPalette palette) {
		this.palette = palette;
		setBackground(palette.BACKGROUND);
		
	}

	@Override
	public void setList(List list) {
		JList<JLabel> a = new JList<JLabel>(list.getList());
		
		add(a);
		a.setBounds(100,100,200,300);
		updateUI();
	}

	

	

	


}
