package factory.validator;

import factory.RandomFactory;
import fileio.repository.BankWalletRepository;
import fileio.repository.DatabaseResult;
import fileio.repository.IRestrictedRepository;
import model.BankWallet;
import model.WalletEntity;

public class BankWalletValidator{
	
	
	public static ValidationResult validateBankWallet(String id){
		IRestrictedRepository<BankWallet> wr = new BankWalletRepository();
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
	
	
	public static boolean isEntityBanknote(WalletEntity entity){
		return entity.getCurrencyName().equals("Banknote");
	}
	
}
