package mediator;

import java.util.ArrayList;
import java.util.List;

import view.decorator.Decorator;

public class UpdatePool {

	protected static List<Decorator> POOL = new ArrayList<Decorator>();
	
	protected static void UPDATE() {
		for(Decorator dec : POOL) {
			dec.set();
		}
	}
}
