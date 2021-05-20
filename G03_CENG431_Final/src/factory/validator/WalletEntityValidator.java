package factory.validator;

public class WalletEntityValidator{
	
	public static ValidationResult validateWalletEntity(String id, String quantity){
		boolean isInteger = Integer.valueOf(id) instanceof Integer;
		boolean isDouble = Double.valueOf(quantity) instanceof Double;
		boolean result = isInteger && isDouble;
		return new ValidationResult(result,"Illegal number format");
	}
}