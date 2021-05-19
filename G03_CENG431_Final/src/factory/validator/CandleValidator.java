package factory.validator;

import factory.objects.CandleParams;

public class CandleValidator {

	
	public static ValidationResult validateHourCandle(CandleParams params) {
		
		ValidationResult highLowResult = validateHighLowValues(params.high, params.low);
		boolean isValid = highLowResult.isValid;

		if (isValid) {
			return new ValidationResult(true, "Validated.");
		}

		return new ValidationResult(highLowResult.messages);

	}

	public static ValidationResult validateDayCandle(CandleParams params) {
	
		ValidationResult highLowResult = validateHighLowValues(params.high, params.low);

		boolean isValid = highLowResult.isValid;	
		

		if (isValid) {
			return new ValidationResult(true, "Validated.");
		}

		return new ValidationResult(highLowResult.messages);
	}

	private static ValidationResult validateHighLowValues(Double high, Double low) {
		ValidationResult result = new ValidationResult("Invalid hig-low result.");
	
		if (high >= low) {
			result = new ValidationResult(true, "Valid high low");
		}
		return result;
	}


}
