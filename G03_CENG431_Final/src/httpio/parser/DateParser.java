package httpio.parser;

import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;
import exception.FileReadException;
import fileio.parser.JSONParser;

public class DateParser {

	/**
	 * The BanknoteParser parses the gotten banknotes.json file content and creates
	 * banknote objects
	 */
	protected DateParser() {
	}

	/**
	 * The function parses gotten file content creates banknote objects.
	 * 
	 * @param fileAll = banknotes.json file content
	 * @throws FileReadException
	 */

	protected Date parseDate(String endpointResult) {
		JSONObject jsonValues;

		Date date;
		try {
			jsonValues = (new JSONParser()).parse(endpointResult); // get json object of file content
			date = parse(jsonValues);
		} catch (JSONException | NumberFormatException e) {
			date = null;
		}
		return date;
	}

	private Date parse(JSONObject jsonObject) throws JSONException,NumberFormatException {
		// iterate json object
		Date date = null;
		
		Object timestamp = jsonObject.get("UnixTimeStamp");
		if(timestamp instanceof String)
		{
			Long time = validateDate(timestamp);
			date= new Date(time);
		}
		
		return date;
	}
	
	private Long validateDate(Object date) throws NumberFormatException{
		return Long.valueOf((String) date);
	}

}
