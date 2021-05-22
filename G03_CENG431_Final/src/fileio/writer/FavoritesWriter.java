package fileio.writer;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.List;

public class FavoritesWriter {

	
	protected static String writeFavoriteTradingPairsXml(Dictionary<String,List<String>> tradingPairs) {
		String result ="<favorites>";
		Enumeration<String> keys = tradingPairs.keys();
		String key = "";
		while(keys.hasMoreElements()) {
			key = keys.nextElement();
			result+=writeFavoriteKeyValueXml(key,tradingPairs.get(key));
		}
		if (result.endsWith(",")) { // if ends with , ignore it
			result = result.substring(0, result.length() - 1);
		}
		result+="</favorites>";
		return result;
	}
	
	private static String writeFavoriteKeyValueXml(String key, List<String> values) {
		String result = "";
		for(int i=0;i<values.size();i++) {
			result+=key+"-"+values.get(i)+",";
		}
		return result;
	}
}
