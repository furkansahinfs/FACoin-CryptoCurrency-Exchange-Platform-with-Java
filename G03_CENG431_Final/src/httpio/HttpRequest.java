package httpio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import enums.ERequestType;

public class HttpRequest {

	private String endpoint;
	private ERequestType type;

	public HttpRequest(String endpoint, ERequestType type) {
		this.endpoint = endpoint;
		this.type = type;
	}
	
	public HttpRequest(){}
	
	public void setParams(String endpoint,ERequestType type){
		this.endpoint = endpoint;
		this.type = type;
	}

	public String getRequest() {
		if (type.equals(ERequestType.GET))
			return "";

		try {
			URL url = new URL(this.endpoint);
			URLConnection urlConnection = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String inputLine;
			String result = "";
			while ((inputLine = reader.readLine()) != null)
				result += inputLine;
			reader.close();
			return result;
		}

		catch (IOException e) {
			return "";
		}

	}
}