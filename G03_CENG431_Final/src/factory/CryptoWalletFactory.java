package factory;

import java.util.Dictionary;
import java.util.Enumeration;

import factory.validator.ValidationResult;
import factory.validator.CryptoWalletValidator;
import model.CryptoWallet;
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
		if(params.isEmpty())
			return walletResult;
		IContainer<WalletEntity> entities = new WalletEntityContainer();
		Enumeration<String> keys = params.keys();
		String key = "";
		while(keys.hasMoreElements()){
			key = keys.nextElement();
			WalletEntity result = super.createWalletEntity(key,params.get(key));
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
			return walletResult;
		
		//  validator ekle, factory düzenle, HTTP get
		walletResult = new CryptoWallet(walletId,entities);
		return walletResult;	
	}
	
}