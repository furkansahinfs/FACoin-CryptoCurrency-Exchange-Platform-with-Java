package factory;

import java.util.Date;

import factory.validator.CandleValidator;
import factory.validator.ValidationResult;
import httpio.repository.SystemProperties;
import model.Candle;
import model.CandleParams;
import model.DayCandle;

public class DayCandleFactory extends CandleFactory {
	public DayCandleFactory() {
		super(null);
		super.setFactory(this);
	}

	@Override
	public Object createEntity(Object args) {
		Candle result = null;
		if (!(args instanceof CandleParams))
			return result;
		CandleParams tempArgs = (CandleParams) args;
		Date nowDate = SystemProperties.SYSTEM_DATE();
		tempArgs.nowDate = nowDate;
		ValidationResult vr = CandleValidator.validateDayCandle(tempArgs);
		if (!vr.isValid)
			return result;
		result = new DayCandle(tempArgs);
		return result;
	}

}