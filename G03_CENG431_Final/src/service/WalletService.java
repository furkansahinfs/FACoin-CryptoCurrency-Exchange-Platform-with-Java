package service;

import model.Currency;
import model.Wallet;
import model.WalletEntity;
import exception.ItemNotFoundException;
import exception.NotSupportedException;

public class WalletService {
	

	public WalletService() {
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

	public void blockBankWalletQuantity(Wallet bankWallet, String banknoteName, Double quantity) {
		try {
			WalletEntity entity = bankWallet.getEntities().getByName(banknoteName);
			entity.setQuantity(entity.getQuantity() - quantity);
		} catch (Exception e) {

		}

	}

	public void blockCryptoWalletQuantity(Wallet cryptoWallet, String cryptoName, Double quantity) {
		try {
			WalletEntity entity = cryptoWallet.getEntities().getByName(cryptoName);
			entity.setQuantity(entity.getQuantity() - quantity);
		} catch (Exception e) {

		}

	}

	public void setBankWalletQuantity(Wallet bankWallet, Currency banknote, Double quantity) {
		try {
			WalletEntity entity = bankWallet.getEntities().getByName(banknote.name);
			entity.setQuantity(entity.getQuantity() + quantity);
		} catch (Exception e) {
			WalletEntity newEntity = new WalletEntity(banknote, quantity);
			bankWallet.getEntities().add(newEntity);
		}

	}

	public void setCryptoWalletQuantity(Wallet cryptoWallet, Currency coin, Double quantity) {
		try {
			WalletEntity entity = cryptoWallet.getEntities().getByName(coin.getName());
			entity.setQuantity(entity.getQuantity() + quantity);
		} catch (Exception e) {
			WalletEntity newEntity = new WalletEntity(coin, quantity);
			cryptoWallet.getEntities().add(newEntity);
		}

	}

}
