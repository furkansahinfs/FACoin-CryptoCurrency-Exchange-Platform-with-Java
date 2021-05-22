         package service;

import model.Currency;
import model.Wallet;
import model.WalletEntity;
import storage.BanknoteName;
import storage.CoinName;
import storage.Name;
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
			entity = cryptoWallet.getEntities().getByName(new CoinName(coinName));
			if (entity.getQuantity() >= coinQuantity) {
				return true;
			}
			return false;
		} catch (ItemNotFoundException | NotSupportedException e) {
			return false;
		}
	}

	public void blockBankWalletQuantity(Wallet bankWallet, String banknoteName, Double quantity) {
		try {
			WalletEntity entity = bankWallet.getEntities().getByName(new BanknoteName(banknoteName));
			entity.setQuantity(entity.getQuantity() - quantity);
			if(entity.getQuantity()==0) {
				bankWallet.getEntities().remove(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void blockCryptoWalletQuantity(Wallet cryptoWallet, String cryptoName, Double quantity) {
		try {
			WalletEntity entity = cryptoWallet.getEntities().getByName(new CoinName(cryptoName));
			entity.setQuantity(entity.getQuantity() - quantity);
			if(entity.getQuantity()==0) {
				cryptoWallet.getEntities().remove(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setBankWalletQuantity(Wallet bankWallet, Currency banknote, Double quantity) {
		try {
			Name name = new BanknoteName(banknote.getName());
			WalletEntity entity = bankWallet.getEntities().getByName(name);
			entity.setQuantity(entity.getQuantity() + quantity);
			if(entity.getQuantity()==0) {
				bankWallet.getEntities().remove(entity);
			}
		} catch (Exception e) {
			WalletEntity newEntity = new WalletEntity(banknote, quantity);
			bankWallet.getEntities().add(newEntity);
		}

	}

	public void setCryptoWalletQuantity(Wallet cryptoWallet, Currency coin, Double quantity) {
		try {
			Name name = new CoinName(coin.getName());
			WalletEntity entity = cryptoWallet.getEntities().getByName(name);
			entity.setQuantity(entity.getQuantity() + quantity);
		} catch (Exception e) {
			WalletEntity newEntity = new WalletEntity(coin, quantity);
			cryptoWallet.getEntities().add(newEntity);
		}

	}

}
