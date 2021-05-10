package factory;

import java.util.Dictionary;

import fileio.CurrencyRepository;
import fileio.DatabaseResult;
import model.Wallet;
import model.WalletEntity;

public abstract class WalletFactory {

	public WalletFactory(){}
	public abstract Wallet createWallet(String id, Dictionary<String,String> params);
	protected WalletEntity createWalletEntity(String currencyId, float quantity){
		WalletEntity entityResult = null;
		DatabaseResult result = (new CurrencyRepository()).getCurrencyById(currencyId);
		Object resultObject = result.getObject();
		if(resultObject!=null){
			entityResult = (WalletEntity)resultObject;
		}
		return entityResult;
	}

}
