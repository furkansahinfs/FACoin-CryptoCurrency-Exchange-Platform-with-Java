package enums;

public enum EBanknotes {
	USD;

	public static boolean isBanknote(String value) {
		try {
			EBanknotes.valueOf(value);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
}
