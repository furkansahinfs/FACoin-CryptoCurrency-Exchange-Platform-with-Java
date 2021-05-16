package factory.validator;

import enums.EBanknotes;
import fileio.repository.CoinRepository;
import fileio.repository.DatabaseResult;
import fileio.repository.IRestrictedRepository;
import model.Currency;

public class BanknoteValidator {

	public static ValidationResult validateBanknote(String currencyName, String currencyId) {
	
		ValidationResult nameResult = validateCurrencyName(currencyName);
		ValidationResult idResult = validateCurrencyId(currencyId);
		
		boolean isValid = nameResult.isValid && idResult.isValid;
		
		if(isValid)
		{
			return new ValidationResult(true,"Validated.");
		}

		return new ValidationResult(nameResult.messages + " - " + idResult.messages);
	}
	
	private static ValidationResult validateCurrencyName(String currencyName)
	{
		ValidationResult validationResult = new ValidationResult("Given currency is not a banknote");
		boolean isBanknote = EBanknotes.isBanknote(currencyName);
		if(isBanknote)
		{
			validationResult = new ValidationResult(true,"Name is validated.");
		}	
		
		return validationResult;
	}
	
	public static ValidationResult validateCurrencyId(String currencyId)
	{
		IRestrictedRepository<Currency> currencyRepository = new CoinRepository();
		DatabaseResult currencyIdResult = currencyRepository.getById(currencyId);
		if (currencyIdResult.getObject() == null) {
			return new ValidationResult(true, "Validated");
		}
	
		return new ValidationResult(false,"Id is not validated.");
	}

	

}
