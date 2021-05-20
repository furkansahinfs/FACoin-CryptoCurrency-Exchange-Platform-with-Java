package view;

import view.color.ColorPalette;
import view.list.List;
import view.list.WalletEntityList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class WalletView extends AppView {


	private static final long serialVersionUID = -1948466354580780395L;

	private List walletEntityList;
	private JScrollPane scrollPane;
	private ColorPalette palette;
	private JButton back;
	private String cryptoWalletId;
	private String bankWalletId;
	private JButton bankWallet;
	private JButton cryptoWallet;

	public WalletView(String cryId, String bankId) {
		this.cryptoWalletId = cryId;
		this.bankWalletId = bankId;
		setLayout(null);
		walletEntityList = new WalletEntityList(null);
		
		back = new JButton("Back");
		back.setFont(new Font("Arial", Font.BOLD, 14));
		back.setBounds(628, 11, 90, 30);
		add(back);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(225, 150, 320, 300);
		scrollPane.setViewportView(walletEntityList);
		add(scrollPane);
		
		bankWallet = new JButton("BNKA");
		bankWallet.setBounds(150, 100, 90, 30);
		add(bankWallet);
		
		cryptoWallet = new JButton("CRYPTO");
		cryptoWallet.setBounds(250, 100, 90, 30);
		add(cryptoWallet);
		
		AppWindow.FRAME.getContentPane().removeAll();
		AppWindow.FRAME.getContentPane().add(this);
		
	}

	/**
	 * The function returns the selected coin.
	 * 
	 * @param String of selected user name
	 */
	public JLabel getListSelected() {
		return walletEntityList.getSelectedValue();
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
		walletEntityList.addMouseListener(listener);
	}
	
	
	private void updateColor() {
		setBackground(palette.BACKGROUND);
		back.setBackground(palette.FIRST_COLOR);
		back.setForeground(palette.BACKGROUND);
		walletEntityList.setBackground(palette.BACKGROUND);
		scrollPane.setBackground(palette.BACKGROUND);
	}

	public void addBackButtonListener(ActionListener listener) {
		back.addActionListener(listener);
	}
	
	public void addBankButtonListener(ActionListener listener) {
		bankWallet.addActionListener(listener);
	}
	
	public void addCryptoButtonListener(ActionListener listener) {
		cryptoWallet.addActionListener(listener);
	}
	
	@Override
	public void setList(List list) {
		walletEntityList.setModel(list.getList());
		walletEntityList.setCellRenderer(new JListRenderer());
		updateUI();
	}

	@Override
	public List getList() {
		return this.walletEntityList;
	}
	
	public String getBankId(){
		return this.bankWalletId;
	}
	
	public String getCryptoId(){
		return this.cryptoWalletId;
	}
}
