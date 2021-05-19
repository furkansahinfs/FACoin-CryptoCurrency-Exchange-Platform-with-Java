package model;

import storage.IContainer;

public class WalletHelper{
	protected static float getTotal(IContainer<WalletEntity> entities, String banknoteName){
		float result = 0;
		for(WalletEntity entity: entities){
			result+=entity.getTotal(banknoteName);
		}
		return result;
	}
}

// TODO change total calculator for dictionary mechanism