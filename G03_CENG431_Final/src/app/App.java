package app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import settings.AppSettings;

public class App {

	 public static void main(String[] args) throws Exception {
			//https://showcase.api.linx.twenty57.net/UnixTime/tounixtimestamp?datetime=now
	        URL oracle = new URL("https://showcase.api.linx.twenty57.net/UnixTime/tounixtimestamp?datetime=now");
//https://min-api.cryptocompare.com/data/v2/histohour?fsym=BTC&tsym=USD&limit=10&api_key="+AppSettings.CRYPTO_API_KEY);
	        URLConnection yc = oracle.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
	        String inputLine;
	        while ((inputLine = in.readLine()) != null) 
	            System.out.println(inputLine);
	        in.close();
new Thread(() -> {
    //Do whatever
}).start();
	    }
}


