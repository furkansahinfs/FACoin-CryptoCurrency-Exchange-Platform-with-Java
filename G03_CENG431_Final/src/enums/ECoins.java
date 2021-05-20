package enums;

public enum ECoins {
	BTC, LTC, DOGE, AVAX, BTT, MATIC, LINK, HT, ETH, FIL, XRP, HOT, DOT, ADA, ALGO;

	public static boolean isCoin(String value) {
		try {
			ECoins.valueOf(value);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
	
}
