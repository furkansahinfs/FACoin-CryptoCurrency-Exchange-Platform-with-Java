package factory.validator;

import factory.RandomFactory;
import fileio.DatabaseResult;
import fileio.WalletRepository;
import model.WalletEntity;

public class WalletValidator{
	
	public static ValidationResult validateCryptoWallet(String id){
		WalletRepository wr = new WalletRepository();
		DatabaseResult dr = wr.getCryptoWalletById(id);
		if(dr.getObject()==null){
			return new ValidationResult(true,"Validated");
		}
		String new_id = RandomFactory.randomId();
		while(wr.getCryptoWalletById(new_id).getObject()!=null){
			new_id = RandomFactory.randomId();
		}
		return new ValidationResult(new_id);
	}
	
	public static ValidationResult validateWalletEntity(String id, String quantity){
		boolean isInteger = Integer.valueOf(id) instanceof Integer;
		boolean isFloat = Float.valueOf(quantity) instanceof Float;
		boolean result = isInteger && isFloat;
		return new ValidationResult(result,"Illegal number format");
	}
	
	public static ValidationResult validateBankWallet(String id){
		WalletRepository wr = new WalletRepository();
		DatabaseResult dr = wr.getBankWalletById(id);
		if(dr.getObject()==null){
			return new ValidationResult(true,"Validated");
		}
		String new_id = RandomFactory.randomId();
		while(wr.getBankWalletById(new_id).getObject()!=null){
			new_id = RandomFactory.randomId();
		}
		return new ValidationResult(new_id);
	}
	
	public static boolean isEntityCoin(WalletEntity entity){
		return entity.getCurrency().equals("Coin");
	}
	
	public static boolean isEntityBanknote(WalletEntity entity){
		return entity.getCurrency().equals("Banknote");
	}
	
}
