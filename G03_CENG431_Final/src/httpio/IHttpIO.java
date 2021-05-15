package httpio;

import java.util.Date;

import exception.HttpRequestException;
import fileio.repository.UpdateData;

public interface IHttpIO {

	public void getHourCandles();
	public UpdateData[] getValues(String endpointPath) throws HttpRequestException;
	public Date getTime(String endpointPath);
	public void readDayCandles(String endpointPath) throws HttpRequestException;
	public void readHourCandles(String enpointPath) throws HttpRequestException;
}
