package model;

import storage.IContainer;

public class WalletHelper{
	protected static float getTotal(IContainer<WalletEntity> entities){
		float result = 0;
		for(WalletEntity entity: entities){
			result+=entity.getTotal();
		}
		return result;
	}
}