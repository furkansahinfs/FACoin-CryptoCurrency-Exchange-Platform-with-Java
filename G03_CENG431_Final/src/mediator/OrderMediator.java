package mediator;

import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;

import controller.OrderController;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import fileio.repository.IRepository;
import fileio.repository.TransactionRepository;
import model.Transaction;
import model.User;
import model.WalletEntity;
import storage.BanknoteName;
import storage.Name;
import view.OrderView;
import view.decorator.DarkThemeDecorator;
import view.decorator.Decorator;
import view.decorator.OrderListDecorator;

public class OrderMediator {

	private final User user;
	private final OrderView view;
	private final OrderController controller;
	
	public OrderMediator(User user) {
		this.user = user;
		view = new OrderView(user.getId());
		Decorator themeDecorator = new DarkThemeDecorator(view);
		Decorator listDecorator = new OrderListDecorator(view);
		UpdatePool.POOL.add(listDecorator);
		controller = new OrderController(this);
	}

	public void back() {
		UpdatePool.POOL.clear();
		getView().setVisible(false);
		HomeMediator mediator = new HomeMediator(user);
	}

	public void rejectTransaction() {
		String password = view.getPassword();
		view.hideReject();
		if(!password.equals(user.getPassword())){
			view.showAlert();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
			}
			view.hideAlert();
			return;
		}
		setRejectProcesses();
		Decorator listDecorator = new OrderListDecorator(view);
	}

	public void rejectTransactionBridge() {
		view.showReject();
	}
	
	private void setRejectProcesses()
	{
		String selectedTransaction = view.getListSelected().getText();;
		String transactionId = selectedTransaction.split(":")[0].strip();
		String banknoteName = selectedTransaction.split("/")[1];
		try {
		
			Transaction transaction = user.getTransactions().getById(transactionId);
			IRepository<Transaction> repo = new TransactionRepository();
			user.getTransactions().remove(transaction);
	
			Name banknoteNameObject = new BanknoteName(banknoteName);
			WalletEntity walletEntity = user.getBankWallet().getEntities().getByName(banknoteNameObject);
			
			walletEntity.setQuantity(walletEntity.getQuantity()+transaction.getCoinValue()*transaction.getCoinQuantity());
			repo.removeEntity(transaction);
		
			repo.saveChanges();
	
		} catch (ItemNotFoundException | NotSupportedException e) {
		}
	}

	/**
	 * @return the view
	 */
	public OrderView getView() {
		return view;
	}
}
