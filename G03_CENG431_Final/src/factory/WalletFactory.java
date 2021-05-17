package factory;

import java.util.Dictionary;

import factory.validator.ValidationResult;
import factory.validator.WalletEntityValidator;
import fileio.repository.BanknoteRepository;
import fileio.repository.CoinRepository;
import fileio.repository.DatabaseResult;
import model.ICurrency;
import model.Wallet;
import model.WalletEntity;

public abstract class WalletFactory {

	public WalletFactory(){}
	public abstract Wallet createWallet(String id, Dictionary<String,String> params);
	protected WalletEntity createWalletEntity(String currencyId, String quantity){
		ValidationResult vr = WalletEntityValidator.validateWalletEntity(currencyId, quantity);
		WalletEntity entityResult = null;
		if(!vr.isValid)
		{
			return entityResult;
		}
		DatabaseResult result = null;
		if(Integer.valueOf(currencyId)<4)
		{
			result = (new CoinRepository()).getById(currencyId);
		}
		else
		{
			result = (new BanknoteRepository()).getById(currencyId);
		}
		
		Object resultObject = result.getObject();
		if(resultObject!=null){
			entityResult = new WalletEntity((ICurrency) resultObject,Float.valueOf(quantity));
		}
		return entityResult;
	}

}
