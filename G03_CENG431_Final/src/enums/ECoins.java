package enums;

public enum ECoins {
	BTC, LTC, DOGE;

	public static boolean isCoin(String value) {
		try {
			ECoins.valueOf(value);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
	
}
