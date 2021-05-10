package model;

import storage.IContainer;

public class BankWallet extends Wallet {

	public BankWallet(String id, IContainer<WalletEntity> entities) {
		super(id, entities);
	}

}
