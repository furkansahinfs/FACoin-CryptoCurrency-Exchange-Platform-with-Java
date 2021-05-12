package enums;

public enum Banknotes {
	USD;

	public static boolean isBanknote(String value) {
		try {
			Banknotes.valueOf(value);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
}
