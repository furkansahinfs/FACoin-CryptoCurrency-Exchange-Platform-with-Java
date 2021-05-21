package service;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import fileio.repository.BanknoteRepository;
import fileio.repository.CoinRepository;
import fileio.repository.DatabaseResult;
import fileio.repository.IRepository;
import fileio.repository.IRestrictedRepository;
import fileio.repository.TransactionRepository;
import model.Currency;
import model.Transaction;
import model.User;
import storage.BanknoteName;
import storage.CoinName;

public class OrderService {

	private IRestrictedRepository<Currency> coins;
	private IRestrictedRepository<Currency> banknotes;
	private IRepository<Transaction> repo;
	private WalletService service;
	private User user;

	public OrderService(User user) {
		this.user = user;
		coins = new CoinRepository();
		banknotes = new BanknoteRepository();
		repo = new TransactionRepository();
		service = new WalletService();
	}

	public void setRejectProcesses(String selectedTransaction)
	{
		String transactionId = selectedTransaction.split(":")[0].strip();
		
		String banknoteName = selectedTransaction.split(":")[1].split("/")[1].strip();
		String coinName = selectedTransaction.split(":")[1].split("/")[0].strip();
		System.out.println("asd"+banknoteName+coinName);
		try {		

			Currency banknote = (Currency) user.getBankWallet().getEntities().getByName(new BanknoteName(banknoteName)).getCurrency();			
			Currency coin = (Currency) user.getCryptoWallet().getEntities().getByName(new CoinName(coinName)).getCurrency();
			Transaction transaction = user.getTransactions().getById(transactionId);
			
			user.getTransactions().remove(transaction);
			if(transaction.getCoinQuantity()>0) {
				
				service.setBankWalletQuantity(user.getBankWallet(), banknote, transaction.getCoinValue()*transaction.getCoinQuantity());
			
			}
			else if(transaction.getCoinQuantity()<0) {
				service.setCryptoWalletQuantity(user.getCryptoWallet(), coin, -1*transaction.getCoinQuantity());
			}
			
			repo.removeEntity(transaction);
		
			repo.saveChanges();
	
		} catch (ItemNotFoundException | NotSupportedException e) {
			e.printStackTrace();
		}
	}

}
