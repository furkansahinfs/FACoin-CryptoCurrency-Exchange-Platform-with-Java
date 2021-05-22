package service;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import fileio.repository.IRepository;
import fileio.repository.TransactionRepository;
import model.Currency;
import model.Transaction;
import model.User;
import storage.BanknoteName;
import storage.CoinName;
import view.decorator.Decorator;

public class OrderService {

	private IRepository<Transaction> repo;
	private WalletService service;
	private User user;

	public OrderService(User user) {
		this.user = user;
		repo = new TransactionRepository();
		service = new WalletService();
	}

	public void setRejectProcesses(String selectedTransaction, Decorator decorator)
	{
		String transactionId = selectedTransaction.split(":")[0].strip();
		
		String banknoteName = selectedTransaction.split(":")[1].split("/")[1].strip();
		String coinName = selectedTransaction.split(":")[1].split("/")[0].strip();
		try {		
			Transaction transaction = user.getTransactions().getById(transactionId);
			
			user.getTransactions().remove(transaction);
			if(transaction.getCoinQuantity()>0) {
				Currency banknote = (Currency) user.getBankWallet().getEntities().getByName(new BanknoteName(banknoteName)).getCurrency();	
				service.setBankWalletQuantity(user.getBankWallet(), banknote, transaction.getCoinValue()*transaction.getCoinQuantity());
			
			}
			else if(transaction.getCoinQuantity()<0) {
				Currency coin = (Currency) user.getCryptoWallet().getEntities().getByName(new CoinName(coinName)).getCurrency();
				service.setCryptoWalletQuantity(user.getCryptoWallet(), coin, -1*transaction.getCoinQuantity());
			}
			
			repo.removeEntity(transaction);
		
			repo.saveChanges();
			decorator.set();
		} catch (ItemNotFoundException | NotSupportedException e) {
			e.printStackTrace();
		}
	}

}
