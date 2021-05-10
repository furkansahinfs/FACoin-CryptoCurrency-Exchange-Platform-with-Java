package model;

import storage.IContainer;

public class Wallet extends AbstractEntity {
	private IContainer<WalletEntity> entities;
	
	public Wallet(String id, IContainer<WalletEntity> entities){
		super(id);
		this.entities = entities;
	}
	
	public float getTotal(){
		return WalletHelper.getTotal(this.entities);
	}
	
}
