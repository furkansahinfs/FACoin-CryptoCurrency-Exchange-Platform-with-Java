package factory.validator;

import enums.ETransaction;
import factory.RandomFactory;
import fileio.repository.BanknoteRepository;
import fileio.repository.CoinRepository;
import fileio.repository.DatabaseResult;
import fileio.repository.IRestrictedRepository;
import fileio.repository.TransactionRepository;
import model.Currency;
import model.Transaction;
public class TransactionValidator {

	public static ValidationResult validatePair(String coinId, String banknoteId) {
		ValidationResult coinResult = validateCoin(coinId);
		ValidationResult baknoteResult = validateBanknote(banknoteId);
		
		boolean areValidationsOK = coinResult.isValid && baknoteResult.isValid;
		ValidationResult validationResult = new ValidationResult("Invalid pair id.");
		if (areValidationsOK) {
			validationResult = new ValidationResult(true, "Validated.");
			return validationResult;
		}

		return validationResult;
	}
	
	public static ValidationResult validateType(String transactionType) {
		ValidationResult validationResult = new ValidationResult("Invalid type.");
		if (ETransaction.isType(transactionType)) {
			validationResult = new ValidationResult(true, "Validated.");
			return validationResult;
		}

		return validationResult;
	}
	
	public static ValidationResult validateQuantites(String coinQuantity, String coinOrderValue) {
		ValidationResult validationResult = new ValidationResult("Invalid quantity.");
		try {
			Double.valueOf(coinQuantity);
			Double.valueOf(coinOrderValue);
			validationResult = new ValidationResult(true, "Validated.");
			return validationResult;
		} catch (Exception e) {
		}
		return validationResult;
	}

	private static ValidationResult validateCoin(String coinId) {
		ValidationResult validationResult = new ValidationResult("Coin is not found in the CoinRepository");
		IRestrictedRepository<Currency> coinRepository = new CoinRepository();
		
		DatabaseResult result = coinRepository.getById(coinId);
		if (result.getObject() != null) {
			validationResult = new ValidationResult(true, "");
		}
		return validationResult;
	}
	
	private static ValidationResult validateBanknote(String banknoteId) {
		ValidationResult validationResult = new ValidationResult("Banknote is not found in the BanknoteRepository");
		IRestrictedRepository<Currency> banknoteRepository = new BanknoteRepository();
		
		DatabaseResult result = banknoteRepository.getById(banknoteId);
		if (result.getObject() != null) {
			validationResult = new ValidationResult(true, "");
		}
		return validationResult;
	}
	
	public static ValidationResult validateTransactionId(String transactionId) {
		if(transactionId==null) {
			return new ValidationResult(false, "Invalidated");
		}
		IRestrictedRepository<Transaction> transactionRepository = new TransactionRepository();
		DatabaseResult transactionIdResult = transactionRepository.getById(transactionId);
		if (transactionIdResult.getObject() == null) {
			return new ValidationResult(true, "Validated");
		}
		String new_id = RandomFactory.randomId();
		while (transactionRepository.getById(new_id).getObject() != null) {
			new_id = RandomFactory.randomId();
		}
		return new ValidationResult(new_id);
	}
	
	



}
