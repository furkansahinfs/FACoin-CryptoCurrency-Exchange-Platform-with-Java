package factory;


import factory.objects.CandleParams;
import factory.validator.CandleValidator;
import factory.validator.ValidationResult;
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
		{
			return result;
		}
			
		CandleParams tempArgs = (CandleParams)args;
		ValidationResult vr = CandleValidator.validateHourCandle(tempArgs);
		if(!vr.isValid)
		{
			return result;
		}
			
		result = new HourCandle(tempArgs);
		return result;
	}
}