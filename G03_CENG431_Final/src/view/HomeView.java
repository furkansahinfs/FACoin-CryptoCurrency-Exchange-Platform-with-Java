package view;

import view.color.ColorPalette;
import view.list.CoinList;
import view.list.List;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class HomeView extends AppView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1948466354580780395L;

	/**
	 * Create the panel.
	 */
	private List coinList;
	private JScrollPane scrollPane;
	private ColorPalette palette;
	private JButton logout;
	private JButton favs;
	private JButton toLow;
	private JButton toHigh;

	public HomeView() {
		setLayout(null);
		coinList = new CoinList(null);
		logout = new JButton("Logout");
		logout.setFont(new Font("Arial", Font.BOLD, 14));
		logout.setBounds(628, 11, 90, 30);
		add(logout);
		scrollPane = new JScrollPane();
		// coinList.setBounds(100,100,200,300);
		scrollPane.setBounds(225, 150, 320, 300);
		// HomeController initialises list using update at the start
		// and in the continuation. Default it is empty.
		scrollPane.setViewportView(coinList);
		add(scrollPane);
		// add(coinList);
		AppWindow.FRAME.getContentPane().add(this);
		
		favs = new JButton("\u2605");
		favs.setBounds(225, 116, 55, 25);
		favs.setFont(new Font("Arial Unicode MS", Font.BOLD, 20));// TODO arial
		add(favs);
		
		toLow = new JButton("\u2193");
		toLow.setBounds(440, 116, 50, 25);
		toLow.setFont(new Font("Arial", Font.BOLD, 20));
		add(toLow);
		
		toHigh = new JButton("\u2191");
		toHigh.setBounds(495, 116, 50, 25);
		toHigh.setFont(new Font("Arial", Font.BOLD, 20));
		add(toHigh);
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
		updateColor();

	}
	
	/**
	 * The function helps for detecting of selecting a outfit of a collection in the
	 * followed users' collections using listener
	 * 
	 * @param listener
	 */
	public void addSelectCoinListener(MouseListener listener) {
		coinList.addMouseListener(listener);
	}
	
	public void addAscendingOrderListener(ActionListener listener) {
		toHigh.addActionListener(listener);
	}
	
	public void addDescendingOrderListener(ActionListener listener) {
		toLow.addActionListener(listener);
	}

	private void updateColor() {
		setBackground(palette.BACKGROUND);
		logout.setBackground(palette.FIRST_COLOR);
		logout.setForeground(palette.BACKGROUND);
		coinList.setBackground(palette.BACKGROUND);
		scrollPane.setBackground(palette.BACKGROUND);
		favs.setBackground(palette.FIRST_COLOR);
		favs.setForeground(palette.BACKGROUND);
		toLow.setBackground(palette.BACKGROUND);
		toLow.setForeground(palette.SECOND_COLOR);
		toHigh.setBackground(palette.BACKGROUND);
		toHigh.setForeground(palette.SECOND_COLOR);
	}

	public void addLogoutButtonListener(ActionListener listener) {
		logout.addActionListener(listener);
	}
	
	@Override
	public void setList(List list) {
		coinList.setModel(list.getList());
		coinList.setCellRenderer(new JListRenderer());
		updateUI();
	}

	@Override
	public List getList() {
		return this.coinList;
	}
}
