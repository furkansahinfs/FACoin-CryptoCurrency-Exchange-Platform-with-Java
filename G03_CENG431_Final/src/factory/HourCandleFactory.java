package factory;

import java.util.Date;

import factory.objects.CandleParams;
import factory.validator.CandleValidator;
import factory.validator.ValidationResult;
import httpio.repository.SystemProperties;
import model.Candle;
import model.HourCandle;

public class HourCandleFactory extends CandleFactory{
	public HourCandleFactory() {
		super(null);
		super.setFactory(this);
	}

	@Override
	public Object createEntity(Object args) {
		Candle result = null;
		if(!(args instanceof CandleParams))
			return result;
		CandleParams tempArgs = (CandleParams)args;
		Date nowDate = SystemProperties.SYSTEM_DATE();
		tempArgs.nowDate = nowDate;
		ValidationResult vr = CandleValidator.validateHourCandle(tempArgs);
		if(!vr.isValid)
			return result;
		result = new HourCandle(tempArgs);
		return result;
	}
}