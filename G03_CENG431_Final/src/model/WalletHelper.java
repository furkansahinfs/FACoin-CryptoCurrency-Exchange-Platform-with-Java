package model;

import storage.IContainer;

public class WalletHelper{
	protected static Double getTotal(IContainer<WalletEntity> entities, String banknoteName){
		Double result = (double)0;
		for(WalletEntity entity: entities){
			result+=entity.getTotal(banknoteName);
		}
		return result;
	}
}

// TODO change total calculator for dictionary mechanism