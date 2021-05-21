package mediator;

import java.awt.Color;
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
import view.color.ColorPalette;
import view.color.DarkTheme;
import view.decorator.DarkThemeDecorator;
import view.decorator.Decorator;
import view.decorator.OrderListDecorator;

public class OrderMediator {

	private final User user;
	private final OrderView view;
	private final OrderController controller;
	private Color approvedColor;
	public OrderMediator(User user) {
		this.approvedColor = (new ColorPalette(new DarkTheme())).SECOND_COLOR;
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
			return;
		}
		JLabel label = view.getListSelected();
		setRejectProcesses(label.getText());
		Decorator listDecorator = new OrderListDecorator(view);
	}

	public void rejectTransactionBridge() {
		JLabel label = view.getListSelected();
		if(label.getForeground().equals(approvedColor) || label == null)
			return;
		view.showReject();
	}
	
	private void setRejectProcesses(String selectedTransaction)
	{
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
