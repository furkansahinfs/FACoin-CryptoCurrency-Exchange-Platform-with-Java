package settings;
public class AppSettings {

	public final static String CRYPTO_API_KEY = "dcced3e12da91813d0ee3d251e38abbb8433aa93f8dcc8d4743d38da0140f142";
	public final static String DATE_ENDPOINT = "https://showcase.api.linx.twenty57.net/UnixTime/tounixtimestamp?datetime=now";
	public final static String CANDLE_PERIOD = "30";
	public static String CANDLE_ENDPOINT = "https://min-api.cryptocompare.com/data/v2/histoday?fsym=%s&tsym=%s&limit="+CANDLE_PERIOD+"&api_key="+CRYPTO_API_KEY;
}
