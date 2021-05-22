package mediator;

import javax.swing.JLabel;

import controller.Consumable;
import controller.IConsumable;
import controller.WalletController;
import enums.ECoins;
import exception.HttpRequestException;
import model.User;
import service.DepositPayService;
import view.WalletView;
import view.decorator.BankWalletListDecorator;
import view.decorator.CryptoWalletListDecorator;
import view.decorator.DarkThemeDecorator;
import view.decorator.JListDecorator;

public class WalletMediator extends Consumable {

	private User user;
	private WalletView view;
	private IConsumable controller;
	private JListDecorator decorator;
	private boolean isBankView = false;
	private boolean isCryptoView = true;
	private DepositPayService service;
	public WalletMediator(User user) {
		this.user = user;
		view = new WalletView(user.getCryptoWallet().getId(), user.getBankWallet().getId());
		decorator = new CryptoWalletListDecorator(view);
		UpdatePool.POOL.add(decorator);
		new DarkThemeDecorator(view);
		service = new DepositPayService();
		service.setComboBoxValues(view);
		controller = new WalletController(this);
	}

	public WalletView getView() {
		return view;
	}

	public void back() {
		controller.supressNotUsed();
		view.setVisible(false);
		UpdatePool.POOL.remove(decorator);
		IConsumable mediator = new HomeMediator(user);
		mediator.supressNotUsed();
	}

	public void getSelectedCoinView() throws HttpRequestException {

		JLabel label = view.getListSelected();

		if (label == null) {
			return;
		}
		String[] splittedTrade = label.getText().split("/");
		if (!ECoins.isCoin(splittedTrade[0])) {
			return;
		}

		String[] splittedItem = label.getText().split(":");
		view.setVisible(false);
		IConsumable mediator = new CoinInfoMediator(splittedItem[0], user);
		mediator.supressNotUsed();

	}

	public void cryptoView() {
		if (!isCryptoView) {
			UpdatePool.POOL.remove(decorator);
			decorator = new CryptoWalletListDecorator(this.view);
			UpdatePool.POOL.add(decorator);
			isCryptoView = true;
			isBankView = false;
		}

	}

	public void bankView() {
		if(!isBankView){
			UpdatePool.POOL.remove(decorator);
			decorator = new BankWalletListDecorator(this.view);
			UpdatePool.POOL.add(decorator);
			isBankView = true;
			isCryptoView = false; 
		}
	}

	public void depositBanknote() {
		String[] values = view.getValues();
		if(values[0] != null && values[1] !=null) {
			service.processDepositRequest(user.getBankWallet(),values[0],values[1]);
		}
		view.hidePay();
	}

	public void showPay() {
		view.showPay();
	}
	
}


