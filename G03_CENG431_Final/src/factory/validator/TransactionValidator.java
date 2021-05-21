package factory.validator;

import enums.ETransaction;
import fileio.repository.BanknoteRepository;
import fileio.repository.CoinRepository;
import fileio.repository.DatabaseResult;
import fileio.repository.IRestrictedRepository;
import model.Currency;

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
	
	public static ValidationResult validateQuantites(String coinQuantity, String banknoteQuantity) {
		ValidationResult validationResult = new ValidationResult("Invalid quantity.");
		try {
			Double.valueOf(coinQuantity);
			Double.valueOf(banknoteQuantity);
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
		if (result == null) {
			validationResult = new ValidationResult(true, "");
		}
		return validationResult;
	}
	
	private static ValidationResult validateBanknote(String banknoteId) {
		ValidationResult validationResult = new ValidationResult("Banknote is not found in the BanknoteRepository");
		IRestrictedRepository<Currency> banknoteRepository = new BanknoteRepository();
		
		DatabaseResult result = banknoteRepository.getById(banknoteId);
		if (result == null) {
			validationResult = new ValidationResult(true, "");
		}
		return validationResult;
	}
	
	



}
