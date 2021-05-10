package model;

import storage.IContainer;

public class CryptoWallet extends Wallet {

	public CryptoWallet(String id, IContainer<WalletEntity> entities) {
		super(id, entities);
	}

}
