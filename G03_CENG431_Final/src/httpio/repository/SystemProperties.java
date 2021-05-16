package httpio.repository;

import java.util.Date;

public class SystemProperties {
	
	private final static Date SYSTEM_CREATION_DATE = new Date(1620604800); // 10 MAY 2021 00:00:00
	private static Date SYSTEM_DATE = SYSTEM_CREATION_DATE;

	public final static Date SYSTEM_DATE() {
		UPDATE_DATE();
		return SYSTEM_DATE;
	}
	
	protected static void setDate(Date date) {
		SYSTEM_DATE = date;
	}
	
	private static void UPDATE_DATE() {
		Date HTTP_DATE = HttpController.updateDate();
		if(isDateValid(HTTP_DATE)) {
			setDate(HTTP_DATE);
		}
	}
	
	private static boolean isDateValid(Date DATE) {
		if(DATE!=null) {
			return validateTimeRange(DATE);
		}
		else {
			return false;
		}
	}
	
	private static boolean validateTimeRange(Date DATE) {
		return SYSTEM_CREATION_DATE.before(DATE);
	}
}
