package service;

import factory.TransactionFactory;
import model.Transaction;
import fileio.repository.TransactionRepository;
import model.User;

public class TransactionService {

	private User user;
	private WalletService walletService;

	public TransactionService(User user) {
		this.user = user;
		walletService = new WalletService();
	}

	public boolean buyCoin(WalletServiceParam params) {

		boolean hasEnoughMoney = walletService.hasEnoughMoney(user.getBankWallet(), params.banknoteName,
				params.coinQuantity, params.coinValue);
		if (hasEnoughMoney) {
			TransactionFactory factory = new TransactionFactory();
			Transaction transaction = factory.createTransaction("0", (params.coinName + "/" + params.banknoteName),
					String.valueOf(params.coinQuantity), String.valueOf(params.coinValue), "Pending");
			if (transaction != null) {
				walletService.blockBankWalletQuantity(user.getCryptoWallet(), params.banknoteName,
						params.coinQuantity * params.coinValue);
				(new TransactionRepository()).addEntity(transaction);
				user.getTransactions().add(transaction);
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
			Transaction transaction = factory.createTransaction("0", (params.coinName + "/" + params.banknoteName),
					String.valueOf(params.coinQuantity*-1), String.valueOf(params.coinValue), "Pending");
			if (transaction != null) {
				walletService.blockCryptoWalletQuantity(user.getBankWallet(), params.coinName, params.coinQuantity);
				(new TransactionRepository()).addEntity(transaction);
				user.getTransactions().add(transaction);
				return true;
			}
		}
		return false;
	}
	
	public void executeOrder(Transaction transaction)
	{
		Double transactionQuantity = transaction.getCoinQuantity();
		if(transactionQuantity>0)
		{
			walletService.setCryptoWalletQuantity(user.getCryptoWallet(), transaction.getCoin(),transactionQuantity );
		}
		
		if(transactionQuantity<0)
		{
			walletService.setBankWalletQuantity(user.getBankWallet(), transaction.getBanknote(), transaction.getCoinValue()*(transactionQuantity*-1) );
		}
	}

}
