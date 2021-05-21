package mediator;

import java.util.ArrayList;
import java.util.List;

public class UpdatePool {

	protected static List<IUpdatable> POOL = new ArrayList<IUpdatable>();
	
	protected static void UPDATE() {
		for(IUpdatable dec : POOL) {
			dec.set();
		}
	}
}
