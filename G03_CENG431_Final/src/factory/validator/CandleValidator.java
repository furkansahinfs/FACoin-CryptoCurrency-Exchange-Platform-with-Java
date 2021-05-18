package factory.validator;



import java.util.Calendar;
import java.util.Date;

import enums.ECandleStatus;
import factory.objects.CandleParams;

public class CandleValidator {

	
	public static ValidationResult validateHourCandle(CandleParams params) {
		
		ValidationResult highLowResult = validateHighLowValues(params.high, params.low);
		ValidationResult hourDateResult = validateHourCandleDate(params.candleDate,params.nowDate);
		boolean isValid = highLowResult.isValid && hourDateResult.isValid;
		if(hourDateResult.isValid)
		{
			params.status = ECandleStatus.CLOSED;
		}
		else
		{
			params.status = ECandleStatus.IN_PROGRESS;
		}

		if (isValid) {
			return new ValidationResult(true, "Validated.");
		}

		return new ValidationResult(highLowResult.messages + " - ");

	}

	public static ValidationResult validateDayCandle(CandleParams params) {
	
		ValidationResult highLowResult = validateHighLowValues(params.high, params.low);
		ValidationResult dayDateResult = validateDayCandleDate(params.candleDate,params.nowDate);
		boolean isValid = highLowResult.isValid && dayDateResult.isValid;
		
		
		if(dayDateResult.isValid)
		{
			params.status = ECandleStatus.CLOSED;
		}
		else
		{
			params.status = ECandleStatus.IN_PROGRESS;
		}
		
		if (isValid) {
			return new ValidationResult(true, "Validated.");
		}

		return new ValidationResult(highLowResult.messages + " - " + dayDateResult.messages);
	}

	private static ValidationResult validateHighLowValues(String high, String low) {
		ValidationResult result = new ValidationResult("Invalid hig-low result.");
	
		if (Float.valueOf(high) >= Float.valueOf(low)) {
			result = new ValidationResult(true, "Valid high low");
		}
		return result;
	}

	@SuppressWarnings("deprecation")
	private static ValidationResult validateDayCandleDate(Date candleDate, Date now) {
		ValidationResult result = new ValidationResult("Invalid day date result");
		
		if ( (candleDate.getTime() + 86401 ) <= now.getTime()) {
			result = new ValidationResult(true, "Valid day date");
		}
		else
		{
			System.out.println(candleDate.getTime() + "-" + now.getTime());
		}

		return result;
	}
	
	@SuppressWarnings("deprecation")
	private static ValidationResult validateHourCandleDate(Date candleDate, Date now) {
		ValidationResult result = new ValidationResult("Invalid hour date result");
		// HTTP Request day time
		if ( (candleDate.getTime() + 3601 )  <= now.getTime()) {
			result = new ValidationResult(true, "Valid");
		}

		return result;
	}

}
