package httpio;

import java.util.Date;
import java.util.List;

import exception.HttpRequestException;
import fileio.repository.UpdateData;

public interface IHttpIO {

	public List<UpdateData> getValues(String endpointPath) throws HttpRequestException;
	public Date getTime(String endpointPath);
	public void readDayCandles(String endpointPath) throws HttpRequestException;
	public void readHourCandles(String endpointPath) throws HttpRequestException;
}
