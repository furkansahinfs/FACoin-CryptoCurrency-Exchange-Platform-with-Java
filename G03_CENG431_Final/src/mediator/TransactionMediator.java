package mediator;

import model.User;
import service.WalletService;
import view.decorator.Decorator;

public class TransactionMediator extends Decorator{

	private User user;
	private WalletService walletService;
	
	public TransactionMediator(User user) {
		this.user = user;
		this.walletService = new WalletService(user);
	}
	
	@Override
	public void set() {
		// TODO Auto-generated method stub
		
	}
	
	public void checkTransactions() {
		
	}
}
