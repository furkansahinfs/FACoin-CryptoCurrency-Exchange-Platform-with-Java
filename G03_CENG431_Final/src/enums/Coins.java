package enums;

public enum Coins {
	BTC, ETH, DOGE, XRP;

	public static boolean isCoin(String value) {
		try {
			Coins.valueOf(value);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
}
