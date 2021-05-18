package enums;

public enum ECoins {
	BTC, LTC, DOGE, AVAX, BTT, MATIC, BNB, HT, ETH, TLM, XRP, HOT, DOT, ADA, ALGO;

	public static boolean isCoin(String value) {
		try {
			ECoins.valueOf(value);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
	
}
