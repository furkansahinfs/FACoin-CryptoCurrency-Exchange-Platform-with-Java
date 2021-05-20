package factory;

import java.util.Dictionary;
import java.util.Enumeration;
import factory.validator.ValidationResult;
import factory.validator.WalletEntityValidator;
import fileio.repository.CoinRepository;
import fileio.repository.DatabaseResult;
import factory.validator.CryptoWalletValidator;
import model.CryptoWallet;
import model.ICurrency;
import model.Wallet;
import model.WalletEntity;
import storage.IContainer;
import storage.WalletEntityContainer;
public class CryptoWalletFactory extends WalletFactory{

	@Override
	public Wallet createWallet(String id, Dictionary<String, String> params) {
		String walletId = id;
		Wallet walletResult = null;
		ValidationResult vr = CryptoWalletValidator.validateCryptoWallet(walletId);
		if(!vr.isValid){
			walletId = vr.messages;
		}
		boolean isNotEligible = false;		

		IContainer<WalletEntity> entities = new WalletEntityContainer();
		if(params.isEmpty())
		{
			//If there is no coin in wallet, create empty wallet.
			walletResult = new CryptoWallet(walletId,entities);
			return walletResult;
		}
		Enumeration<String> keys = params.keys();
		String key = "";
		while(keys.hasMoreElements()){
			
			key = keys.nextElement();
		
			WalletEntity result = createWalletEntity(key,params.get(key));
			if(result==null){
				isNotEligible = true;
				break;
			}
			
			if(!CryptoWalletValidator.isEntityCoin(result)){
				isNotEligible = true;
				break;
			}
			entities.add(result);	
		}
		if(isNotEligible)
		{
			return walletResult;
		}
		walletResult = new CryptoWallet(walletId,entities);
		return walletResult;
				
	}

	@Override
	protected WalletEntity createWalletEntity(String currencyId, String quantity) {
		ValidationResult vr = WalletEntityValidator.validateWalletEntity(currencyId, quantity);
		WalletEntity entityResult = null;
		if(!vr.isValid)
		{
			return entityResult;
		}
		DatabaseResult result = null;
		result = (new CoinRepository()).getById(currencyId);
		
		Object resultObject = result.getObject();
		if(resultObject!=null){
			entityResult = new WalletEntity((ICurrency) resultObject,Double.valueOf(quantity));
		}
		return entityResult;
	}
	
}