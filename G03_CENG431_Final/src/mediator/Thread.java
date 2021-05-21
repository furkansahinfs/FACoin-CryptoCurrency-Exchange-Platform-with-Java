package mediator;


import java.util.concurrent.ScheduledExecutorService;

public class Thread {
	protected static ScheduledExecutorService EXECUTOR = null;
	public static int MUTEX = 0;

	public static void LOCK_MUTEX(int mutex) {
		while(mutex==1);
		mutex++;
	}
	
	public static void UNLOCK_MUTEX(int mutex) {
		mutex--;
	}
}
