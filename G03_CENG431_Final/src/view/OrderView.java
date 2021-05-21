package view;

import view.color.ColorPalette;
import view.list.CoinList;
import view.list.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class OrderView extends AppView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1948466354580780395L;

	/**
	 * Create the panel.
	 */
	private List orderList;
	private JScrollPane scrollPane;
	private ColorPalette palette;
	private JButton back;
	private final String userId;
	
	public OrderView(String userId) {
		this.userId = userId;
		setLayout(null);
		
		back = new JButton("Back");
		back.setBounds(628, 50, 90, 30);
		back.setFont(new Font("Arial", Font.BOLD, 20));// TODO arial
		add(back);
		
		orderList = new CoinList(null);
		scrollPane = new JScrollPane();
		// coinList.setBounds(100,100,200,300);
		scrollPane.setBounds(225, 150, 320, 300);
		// HomeController initialises list using update at the start
		// and in the continuation. Default it is empty.
		scrollPane.setViewportView(orderList);
		add(scrollPane);
		// add(coinList);
		AppWindow.FRAME.getContentPane().removeAll();
		AppWindow.FRAME.getContentPane().add(this);

	}

	/**
	 * The function returns the selected coin.
	 * 
	 * @param String of selected user name
	 */
	public JLabel getListSelected() {
		return orderList.getSelectedValue();
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
		orderList.addMouseListener(listener);
	}
	
	public void addBackButtonListener(ActionListener listener) {
		back.addActionListener(listener);
	}
	
	


	private void updateColor() {
		setBackground(palette.BACKGROUND);

		orderList.setBackground(palette.BACKGROUND);
		scrollPane.setBackground(palette.BACKGROUND);
		back.setBackground(palette.FIRST_COLOR);
		back.setForeground(palette.BACKGROUND);
	}

	
	@Override
	public void setList(List list) {
		orderList.setModel(list.getList());
		orderList.setCellRenderer(new JListRenderer());
		updateUI();
	}

	@Override
	public List getList() {
		return this.orderList;
	}
	
	public final String getUserId() { 
		return this.userId;
	}
}
