package service;

import factory.TransactionFactory;
import model.Transaction;
import fileio.repository.IRepository;
import fileio.repository.TransactionRepository;
import model.User;

public class TransactionService {

	private User user;
	private WalletService walletService;
	private IRepository<Transaction> repo;

	public TransactionService(User user) {
		this.user = user;
		walletService = new WalletService();
		repo = new TransactionRepository();
	}

	public boolean buyCoin(WalletServiceParam params) {
		boolean hasEnoughMoney = walletService.hasEnoughMoney(user.getBankWallet(), params.banknoteName,
				params.coinQuantity, params.coinValue);
		if (hasEnoughMoney) {
			TransactionFactory factory = new TransactionFactory();
			Transaction transaction = factory.createTransaction("0", (params.coinId + "-" + params.banknoteId),
					String.valueOf(params.coinQuantity), String.valueOf(params.coinValue), "Pending");
			if (transaction != null) {
				walletService.blockBankWalletQuantity(user.getBankWallet(), params.banknoteName,
						params.coinQuantity * params.coinValue);
				repo.addEntity(transaction);
				user.getTransactions().add(transaction);
				repo.saveChanges();
				
				return true;
			}
		}
		return false;
	}

	public boolean sellCoin(WalletServiceParam params) {
	
		boolean hasEnoughCoin = walletService.hasEnoughCoin(user.getCryptoWallet(), params.coinName,
				params.coinQuantity);
		if (hasEnoughCoin) {
			TransactionFactory factory = new TransactionFactory();
			Transaction transaction = factory.createTransaction("0", (params.coinId + "-" + params.banknoteId),
					String.valueOf(params.coinQuantity*-1), String.valueOf(params.coinValue), "Pending");
			if (transaction != null) {
				walletService.blockCryptoWalletQuantity(user.getCryptoWallet(), params.coinName, params.coinQuantity);
				repo.addEntity(transaction);
				user.getTransactions().add(transaction);
				repo.saveChanges();
				return true;
			}
		}
		return false;
	}
	
	public void executeOrder(Transaction transaction, Double coinUpdatedValue)
	{
		
		Double transactionQuantity = transaction.getCoinQuantity();
		if(transactionQuantity>0)
		{
			if(transaction.getCoinValue()>=coinUpdatedValue)
			{
				walletService.setCryptoWalletQuantity(user.getCryptoWallet(), transaction.getCoin(),transactionQuantity );
				transaction.approveTransaction();
			}
		
		}
		if(transactionQuantity<0)
		{
			if(transaction.getCoinValue()>coinUpdatedValue)
				return;
			walletService.setBankWalletQuantity(user.getBankWallet(), transaction.getBanknote(), transaction.getCoinValue()*(transactionQuantity*-1));
			transaction.approveTransaction();
		}
	}

}
