package settings;import javax.swing.ImageIcon;

public class AppSettings {

	public final static String CRYPTO_API_KEY = "dcced3e12da91813d0ee3d251e38abbb8433aa93f8dcc8d4743d38da0140f142";
	public final static String DATE_ENDPOINT = "https://showcase.api.linx.twenty57.net/UnixTime/tounixtimestamp?datetime=now";
	public final static String CANDLE_PERIOD = "30";
	public static String CANDLE_ENDPOINT = "https://min-api.cryptocompare.com/data/v2/histoday?fsym=%s&tsym=%s&limit="+CANDLE_PERIOD+"&api_key="+CRYPTO_API_KEY;
	public final static int HEIGHT = 600;
	public final static int WIDTH = 800;
	public final static ImageIcon LOGO = new ImageIcon("data//FaclogoLittle.png");
	public static int SCREEN_X = 0;
	public static int SCREEN_Y = 0;
}
