package mediator;


import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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

	public static void sleep(int i) {
		try {
			TimeUnit.SECONDS.sleep(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
