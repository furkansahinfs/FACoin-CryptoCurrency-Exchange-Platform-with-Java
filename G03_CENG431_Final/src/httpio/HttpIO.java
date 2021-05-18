package httpio;

import java.util.Date;
import java.util.List;

import org.json.JSONException;
import enums.ECoins;
import enums.ERequestType;
import exception.HttpRequestException;
import fileio.repository.UpdateData;
import httpio.parser.Parser;

public class HttpIO implements IHttpIO {

	private Parser parser;
	private HttpRequest httpRequest;

	public HttpIO() {
		parser = new Parser();
		httpRequest = new HttpRequest();
	}

	public List<UpdateData> getValues(String endpointPath) {
		httpRequest.setParams(endpointPath, ERequestType.GET);
		String httpContext = httpRequest.getRequest();
		List<UpdateData> updateDataList = parser.parseUpdatedValues(httpContext);
		return updateDataList;
	}

	@Override
	public Date getTime(String endpointPath) {
		httpRequest.setParams(endpointPath, ERequestType.GET);
		String httpContext = httpRequest.getRequest();
		Date date = parser.parseDate(httpContext);
		return date;
	}

	@Override
	public void readDayCandles(String endpointPath) throws HttpRequestException {
		httpRequest.setParams(endpointPath, ERequestType.GET);
		String httpContext = httpRequest.getRequest();
		String coinName = extractCoinName(endpointPath).name();
		try {
			parser.parseDayCandles(httpContext, coinName);
		} catch (JSONException e) {
			throw new HttpRequestException(e.getMessage());
		}

	}

	@Override
	public void readHourCandles(String endpointPath) throws HttpRequestException {
		httpRequest.setParams(endpointPath, ERequestType.GET);
		String httpContext = httpRequest.getRequest();
		String coinName = extractCoinName(endpointPath).name();
		try {
			parser.parseHourCandles(httpContext, coinName);
		} catch (JSONException e) {
			throw new HttpRequestException(e.getMessage());
		}
	}

	private ECoins extractCoinName(String url) {
		int eqIndex = url.indexOf("=") + 1;
		int andIndex = url.indexOf("&");
		String coinName = url.substring(eqIndex, andIndex);
		return ECoins.valueOf(coinName);
	}

}
