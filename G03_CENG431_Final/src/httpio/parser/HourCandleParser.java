package httpio.parser;

import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import enums.CandleStatus;
import factory.AbstractFactory;
import factory.HourCandleFactory;
import fileio.parser.JSONParser;
import fileio.repository.IRepository;
import httpio.repository.HourCandleRepository;
import model.Candle;
import model.CandleParams;


public class HourCandleParser {
	
	private AbstractFactory abstractFactory;
	/**
	 * The BanknoteParser parses the gotten banknotes.json file content and creates
	 * banknote objects
	 */
	protected HourCandleParser() {
		this.abstractFactory = new AbstractFactory(new HourCandleFactory());
	}

	/**
	 * The function parses gotten file content creates banknote objects.
	 * 
	 * @param fileAll = banknotes.json file content
	 * @throws JSONException 
	 */

	protected void parseHourCandles(String endpointResult, String coinName) throws JSONException {
		IRepository<Candle> hourCandleRepository = new HourCandleRepository();
		JSONObject jsonValues;
		jsonValues = (new JSONParser()).parse(endpointResult); // get json object of file content
		parse(jsonValues, coinName, hourCandleRepository);

	}

	private void parse(JSONObject jsonObject, String coinName, IRepository<Candle> hourCandleRepository)
			throws JSONException {
		// iterate json object
		String status = jsonObject.getString("Response");
		
		if(status.equals("Success"))
		{
			JSONArray data = ((JSONObject) jsonObject.get("Data")).getJSONArray("Data");
			for (int i=0;i<data.length();i++)
			{
				JSONObject candleData = (JSONObject) data.get(i);
				String time,high,low,open,close,volume;
				time = candleData.getString("time");
				high = candleData.getString("high");
				low = candleData.getString("low");
				open = candleData.getString("open");
				close = candleData.getString("close");
				volume = candleData.getString("volumeTo");
				Date date = new Date(Long.valueOf(time));
				CandleParams params = new CandleParams(coinName, date, null, high, low,open, close, volume, CandleStatus.CLOSED);
				Candle candle = createCandle(params);
				if(candle != null)
				{
					hourCandleRepository.addEntity(candle);
				}
			}
			
		}
		else
		{
			throw new JSONException("There is a problem occured while requesting endpoint");
		}
		
	}

	private Candle createCandle(CandleParams params){
		Candle result = null;
		result = (Candle) abstractFactory.createEntity(params);
		return result;
	}

}
