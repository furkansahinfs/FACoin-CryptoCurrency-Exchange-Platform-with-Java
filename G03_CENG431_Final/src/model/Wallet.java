package model;

import storage.IContainer;

public abstract class Wallet extends AbstractEntity {
	private IContainer<WalletEntity> entities;
	
	public Wallet(String id, IContainer<WalletEntity> entities){
		super(id);
		this.entities = entities;
	}
	
	public Double getTotal(String banknoteName){
		return WalletHelper.getTotal(this.entities,banknoteName);
	}
	
	public final IContainer<WalletEntity> getEntities(){
		return this.entities;
	}
	
}
