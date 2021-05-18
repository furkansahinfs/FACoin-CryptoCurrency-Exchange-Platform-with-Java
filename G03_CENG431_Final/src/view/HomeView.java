package view;

import view.color.ColorPalette;
import view.list.CoinList;
import view.list.List;
import javax.swing.JButton;

import javax.swing.JScrollPane;
import java.awt.Font;
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

	public HomeView() {
		setLayout(null);
		coinList = new CoinList(null);
		logout = new JButton("Logout");
		logout.setFont(new Font("Arial", Font.PLAIN, 12));
		logout.setBounds(628, 11, 82, 23);
		add(logout);
		scrollPane = new JScrollPane();
		// coinList.setBounds(100,100,200,300);
		scrollPane.setBounds(180, 40, 320, 300);
		// HomeController initialises list using update at the start
		// and in the continuation. Default it is empty.
		scrollPane.setViewportView(coinList);
		add(scrollPane);
		// add(coinList);
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

	private void updateColor() {
		setBackground(palette.BACKGROUND);
		logout.setBackground(palette.FIRST_COLOR);
		logout.setForeground(palette.BACKGROUND);
		coinList.setBackground(palette.BACKGROUND);
		scrollPane.setBackground(palette.BACKGROUND);
	}

	@Override
	public void setList(List list) {
		coinList.setModel(list.getList());
		coinList.setCellRenderer(new JListRenderer());
		updateUI();
	}

}
