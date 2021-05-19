package factory;



import factory.objects.CandleParams;
import factory.validator.CandleValidator;
import factory.validator.ValidationResult;
import model.Candle;
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
		ValidationResult vr = CandleValidator.validateDayCandle(tempArgs);
		if (!vr.isValid)
		{
			System.out.println(vr.messages);
			return result;
		}
			
		result = new DayCandle(tempArgs);
		return result;
	}

}