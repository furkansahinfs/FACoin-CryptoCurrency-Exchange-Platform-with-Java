package mediator;

import model.Transaction;
import model.User;
import service.TransactionService;

public class TransactionMediator implements IUpdatable{

	private User user;
	private TransactionService transactionService;
	public TransactionMediator(User user) {
		this.user = user;
		this.transactionService = new TransactionService(user);
	}
	
	@Override
	public void set() {
		checkTransactions();
	}
	
	public void checkTransactions() {
		for(Transaction transaction: user.getTransactions()) {
			if(transaction.getTransactionState().equals("Pending")) {
				Double realValue = transaction.getCoin().getValue().get(transaction.getBanknote().getName());
				if(realValue>=transaction.getCoinValue()) {
					transactionService.executeOrder(transaction);
					transaction.approveTransaction();
				}
			}
		}
	}
}
