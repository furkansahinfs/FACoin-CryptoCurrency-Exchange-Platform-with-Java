package service;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import fileio.repository.BanknoteRepository;
import fileio.repository.CoinRepository;
import fileio.repository.DatabaseResult;
import fileio.repository.TransactionRepository;
import fileio.repository.IRestrictedRepository;
import model.Banknote;
import model.Coin;
import model.Currency;
import model.ICurrency;
import model.Transaction;
import model.User;
import factory.TransactionFactory;
import factory.AbstractFactory;
import model.Wallet;
import model.WalletEntity;

public class WalletService {
	// TODO ayýrrrrr
	private IRestrictedRepository<Currency> coins;
	private IRestrictedRepository<Currency> banknotes;

	private User user;

	public WalletService(User user) {
		this.user = user;
	}

	public boolean buyCoin(WalletServiceParam params) {
		boolean hasEnoughMoney = hasEnoughMoney(user.getBankWallet(), params.banknoteName, params.coinQuantity, params.coinValue);
		if (hasEnoughMoney) {
			TransactionFactory factory =new TransactionFactory();
			Transaction transaction = factory.createTransaction("0", (params.coinName + "/" + params.banknoteName),
					String.valueOf(params.coinQuantity), String.valueOf(params.coinValue), "Pending");
			if (transaction != null) {
				blockBankWalletQuantity(user.getCryptoWallet(), params.banknoteName, params.coinQuantity*params.coinValue);
				(new TransactionRepository()).addEntity(transaction);
				user.getTransactions().add(transaction);
				return true;
			}
		}		
		return false;
	}

	public boolean sellCoin(WalletServiceParam params) {		
		boolean hasEnoughCoin = hasEnoughCoin(user.getCryptoWallet(), params.coinName, params.coinQuantity);
		if (hasEnoughCoin) {
			TransactionFactory factory =new TransactionFactory();
			Transaction transaction = factory.createTransaction("0", (params.coinName + "/" + params.banknoteName),
					String.valueOf(params.coinQuantity), String.valueOf(params.coinValue), "Pending");
			if (transaction != null) {
				blockCryptoWalletQuantity(user.getBankWallet(), params.coinName, params.coinQuantity);
				(new TransactionRepository()).addEntity(transaction);
				user.getTransactions().add(transaction);
				return true;
			}
		}
		return false;
	}

	public boolean hasEnoughMoney(Wallet bankWallet, String banknoteName, Double coinQuantity, Double coinValue) {
		if (bankWallet.getTotal(banknoteName) >= coinQuantity * coinValue) {
			return true;
		}
		return false;
	}

	public boolean hasEnoughCoin(Wallet cryptoWallet, String coinName, Double coinQuantity) {
		WalletEntity entity;
		try {
			entity = cryptoWallet.getEntities().getByName(coinName);
			if (entity.getQuantity() <= coinQuantity) {
				return true;
			}
			return false;
		} catch (ItemNotFoundException | NotSupportedException e) {
			return false;
		}
	}

	private void setBankWalletQuantity(Wallet bankWallet, String banknoteName, Double quantity) {
		try {
			WalletEntity entity = bankWallet.getEntities().getByName(banknoteName);
			entity.setQuantity(entity.getQuantity() + quantity);
		} catch (Exception e) {
			DatabaseResult result = (new BanknoteRepository()).getByName(banknoteName);
			ICurrency currency = (Banknote) result.getObject();
			WalletEntity newEntity = new WalletEntity(currency, quantity);
			bankWallet.getEntities().add(newEntity);
		}

	}

	private void setCryptoWalletQuantity(Wallet cryptoWallet, String cryptoName, Double quantity) {
		try {
			WalletEntity entity = cryptoWallet.getEntities().getByName(cryptoName);
			entity.setQuantity(entity.getQuantity() + quantity);
		} catch (Exception e) {
			DatabaseResult result = (new CoinRepository()).getByName(cryptoName);
			ICurrency currency = (Coin) result.getObject();
			WalletEntity newEntity = new WalletEntity(currency, quantity);
			cryptoWallet.getEntities().add(newEntity);
		}

	}

	private void blockBankWalletQuantity(Wallet bankWallet, String banknoteName, Double quantity) {
		try {
			WalletEntity entity = bankWallet.getEntities().getByName(banknoteName);
			entity.setQuantity(entity.getQuantity() - quantity);
		} catch (Exception e) {

		}

	}

	private void blockCryptoWalletQuantity(Wallet cryptoWallet, String cryptoName, Double quantity) {
		try {
			WalletEntity entity = cryptoWallet.getEntities().getByName(cryptoName);
			entity.setQuantity(entity.getQuantity() - quantity);
		} catch (Exception e) {

		}

	}

}
