package httpio;

import java.util.Date;
import org.json.JSONException;
import enums.Coins;
import enums.RequestType;
import exception.HttpRequestException;
import fileio.repository.UpdateData;
import httpio.parser.Parser;

public class HttpIO implements IHttpIO{


	private Parser parser;
	private HttpRequest httpRequest;

	public HttpIO(){
		parser = new Parser();
		httpRequest = new HttpRequest();
	}

	public UpdateData[] getValues(String endpointPath){
		httpRequest.setParams(endpointPath, RequestType.GET);
		String httpContext = httpRequest.getRequest();
		UpdateData[] updateDataList = parser.parseUpdatedValues(httpContext);
		return updateDataList;
	}

	@Override
	public void getHourCandles() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date getTime(String endpointPath) {
		httpRequest.setParams(endpointPath, RequestType.GET);
		String httpContext = httpRequest.getRequest();
		Date date = parser.parseDate(httpContext);
		return date;
	}

	@Override
	public void readDayCandles(String endpointPath) throws HttpRequestException {
		httpRequest.setParams(endpointPath, RequestType.GET);
		String httpContext = httpRequest.getRequest();
		String coinName = extractCoinName(endpointPath).name();
		try {
			parser.parseDayCandles(httpContext, coinName);
		} catch (JSONException e) {
			throw new HttpRequestException(e.getMessage());
		}
		
	}

	@Override
	public void readHourCandles(String endpointPath) throws HttpRequestException{
		httpRequest.setParams(endpointPath, RequestType.GET);
		String httpContext = httpRequest.getRequest();
		String coinName = extractCoinName(endpointPath).name();
		try {
			parser.parseHourCandles(httpContext,coinName);
		} catch (JSONException e) {
			throw new HttpRequestException(e.getMessage());
		}
	}

	private Coins extractCoinName(String url){
		int eqIndex = url.indexOf("=")+1;
		int andIndex = url.indexOf("&");
		String coinName = url.substring(eqIndex, andIndex);
		return Coins.valueOf(coinName);
	}

}
