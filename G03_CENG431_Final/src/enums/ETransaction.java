package enums;
public enum ETransaction{
	Approved,Pending;
	
	public static boolean isType(String value) {
		try {
			ETransaction.valueOf(value);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
}