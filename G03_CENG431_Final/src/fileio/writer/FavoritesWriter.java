package fileio.writer;

import java.util.Dictionary;
import java.util.Enumeration;

public class FavoritesWriter {

	
	protected static String writeFavoriteTradingPairsXml(Dictionary<String,String> tradingPairs) {
		String result =" <favorites>";
		Enumeration<String> keys = tradingPairs.keys();
		String key = "";
		while(keys.hasMoreElements()) {
			key = keys.nextElement();
			result+=key+"-"+tradingPairs.get(key)+",";
		}
		if (result.endsWith(",")) { // if ends with , ignore it
			result = result.substring(0, result.length() - 1);
		}
		result+="</favorites>";
		return result;
	}
}
