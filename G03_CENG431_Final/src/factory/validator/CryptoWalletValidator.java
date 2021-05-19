package factory.validator;

import factory.RandomFactory;
import fileio.repository.CryptoWalletRepository;
import fileio.repository.DatabaseResult;
import fileio.repository.IRestrictedRepository;
import model.CryptoWallet;
import model.WalletEntity;

public class CryptoWalletValidator{
	
	public static ValidationResult validateCryptoWallet(String id){
		IRestrictedRepository<CryptoWallet> wr = new CryptoWalletRepository();
		DatabaseResult dr = wr.getById(id);
		if(dr.getObject()==null){
			return new ValidationResult(true,"Validated");
		}
		String new_id = RandomFactory.randomId();
		while(wr.getById(new_id).getObject()!=null){
			new_id = RandomFactory.randomId();
		}
		return new ValidationResult(new_id);
		
	}
	
	
	public static boolean isEntityCoin(WalletEntity entity){
		return entity.getCurrencyName().equals("Coin");
	}
	
	
}
