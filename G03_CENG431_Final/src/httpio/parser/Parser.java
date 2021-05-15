package httpio.parser;

import java.util.Date;

import org.json.JSONException;

import fileio.repository.UpdateData;

/**
 * This function is a middleware for parser classes
 *
 */
public class Parser {
	private UpdateParser updateParser;
	private DateParser dateParser;
	private DayCandleParser dayParser;
	private HourCandleParser hourParser;

	public Parser() {
		this.updateParser = new UpdateParser();
		this.dateParser = new DateParser();
		this.hourParser = new HourCandleParser();
		this.dayParser = new DayCandleParser();
	}

	/**
	 * The function parses gotten file content and returns the user container which
	 * holds created users
	 * 
	 * @param fileAll = users.xml file content
	 * @return User Container
	 * @throws XMLException
	 */
	public UpdateData[] parseUpdatedValues(String fileAll) {
		UpdateData[] datas = updateParser.parseValues(fileAll);
		return datas;
	}
	
	public Date parseDate(String fileAll){
		Date date = dateParser.parseDate(fileAll);
		return date;
	}
	
	public void parseDayCandles(String fileAll, String coinName) throws JSONException{
		dayParser.parseDayCandles(fileAll, coinName);
	}
	
	public void parseHourCandles(String fileAll,  String coinName) throws JSONException{
		hourParser.parseHourCandles(fileAll, coinName);
	}

}
