package factory.validator;

public class WalletEntityValidator{
	
	public static ValidationResult validateWalletEntity(String id, String quantity){
		boolean isInteger = Integer.valueOf(id) instanceof Integer;
		boolean isFloat = Float.valueOf(quantity) instanceof Float;
		boolean result = isInteger && isFloat;
		return new ValidationResult(result,"Illegal number format");
	}
}