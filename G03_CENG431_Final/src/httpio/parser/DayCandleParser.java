package httpio.parser;

import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import factory.AbstractFactory;
import factory.DayCandleFactory;
import factory.objects.CandleParams;
import fileio.parser.JSONParser;
import fileio.repository.IRepository;
import httpio.repository.DayCandleRepository;
import model.Candle;

public class DayCandleParser {

	private AbstractFactory abstractFactory;

	/**
	 * The BanknoteParser parses the gotten banknotes.json file content and creates
	 * banknote objects
	 */
	protected DayCandleParser() {
		this.abstractFactory = new AbstractFactory(new DayCandleFactory());
	}

	/**
	 * The function parses gotten file content creates banknote objects.
	 * 
	 * @param fileAll = banknotes.json file content
	 * @throws JSONException
	 */

	protected void parseDayCandles(String endpointResult, String coinName) throws JSONException {
		IRepository<Candle> dayCandleRepository = new DayCandleRepository();
		JSONObject jsonValues;
		jsonValues = (new JSONParser()).parse(endpointResult); // get json object of file content
		parse(jsonValues,coinName, dayCandleRepository);


	}

	private void parse(JSONObject jsonObject,String coinName, IRepository<Candle> dayCandleRepository)
			throws JSONException  {
		// iterate json object
		String status = jsonObject.getString("Response");
		
		if(status.equals("Success"))
		{
			JSONArray data = ((JSONObject) jsonObject.get("Data")).getJSONArray("Data");
			for (int i=0;i<data.length();i++)
			{
				JSONObject candleData = (JSONObject) data.get(i);
				Double high,low,open,close,volume;
				Long time;
				time = candleData.getLong("time");
				high = candleData.getDouble("high");
				low = candleData.getDouble("low");
				open = candleData.getDouble("open");
				close = candleData.getDouble("close");
				volume = candleData.getDouble("volumeto");
				Date date = new Date(Long.valueOf(time));
				CandleParams params = new CandleParams(date,high,low, open, close, volume);
	
				Candle candle = createDayCandle(params);
				if(candle != null)
				{
					dayCandleRepository.addEntity(candle);
				}
				
			}
			
		}
		else
		{
			throw new JSONException("There is a problem occured while requesting endpoint");
		}
		
	}
	
	private Candle createDayCandle(CandleParams args){
		Candle result = null;
		result = (Candle) abstractFactory.createEntity(args);
		return result;
	}

}
