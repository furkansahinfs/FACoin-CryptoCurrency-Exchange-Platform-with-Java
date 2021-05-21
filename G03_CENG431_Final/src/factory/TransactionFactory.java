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

	
	public Transaction createTransaction(String transactionId, String pair, String coinQuantity, String coinOrderValue, String transactionType) {
		
		String[] pairNames = pair.split("-");
		ValidationResult pairResult = TransactionValidator.validatePair(pairNames[0], pairNames[1]);
		ValidationResult quantityResult =  TransactionValidator.validateQuantites(coinQuantity, coinOrderValue);
		ValidationResult typeResult = TransactionValidator.validateType(transactionType);
		boolean isValidated = pairResult.isValid && quantityResult.isValid && typeResult.isValid; 
		if (!isValidated) {
			return null;
		}
		ValidationResult idResult = TransactionValidator.validateTransactionId(transactionId);
		String id = transactionId;
		if(!idResult.isValid)
			id = idResult.messages;
		
		DatabaseResult gottenCoin = (new CoinRepository()).getById(pairNames[0]);
		DatabaseResult gottenBanknote = (new BanknoteRepository()).getById(pairNames[1]);
		State state = new Pending();
		if(transactionType.equals("Approved"))
		{
			state = new Approved();
		}
		TransactionType type = new TransactionType(state);
		
		Transaction transaction = new Transaction(id,(Currency) gottenCoin.getObject(), (Currency) gottenBanknote.getObject(), type, Double.valueOf(coinQuantity), Double.valueOf(coinOrderValue));
		
		return transaction;
	}

	

}