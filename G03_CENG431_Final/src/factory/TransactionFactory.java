package factory;

import factory.validator.TransactionValidator;
import factory.validator.ValidationResult;
import fileio.repository.BanknoteRepository;
import fileio.repository.CoinRepository;
import fileio.repository.DatabaseResult;
import model.Currency;
import model.Transaction;
import state.*;
import state.TransactionType;

public class TransactionFactory {

	
	public Transaction createTransaction(String pair, String coinQuantity, String banknoteQuantity, String transactionType) {
		
		String[] pairNames = pair.split("-");
		ValidationResult pairResult = TransactionValidator.validatePair(pairNames[0], pairNames[1]);
		ValidationResult quantityResult =  TransactionValidator.validateQuantites(coinQuantity, banknoteQuantity);
		ValidationResult typeResult = TransactionValidator.validateType(transactionType);
		boolean isValidated = pairResult.isValid && quantityResult.isValid && typeResult.isValid;
		if (!isValidated) {
			return null;
		}
		
		DatabaseResult gottenCoin = (new CoinRepository()).getById(pairNames[0]);
		DatabaseResult gottenBanknote = (new BanknoteRepository()).getById(pairNames[0]);
		State state = new Pending();
		if(transactionType.equals("Approve"))
		{
			state = new Approved();
		}
		TransactionType type = new TransactionType(state);
		
		Transaction transaction = new Transaction((Currency) gottenCoin.getObject(), (Currency) gottenBanknote.getObject(), type, Double.valueOf(coinQuantity), Double.valueOf(banknoteQuantity));
		
		return transaction;
	}

	

}