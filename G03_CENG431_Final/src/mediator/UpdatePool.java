package mediator;

import java.util.ArrayList;
import java.util.List;

import view.decorator.JListDecorator;

public class UpdatePool {

	protected static List<JListDecorator> POOL = new ArrayList<JListDecorator>();
	
	protected static void UPDATE() {
		for(JListDecorator dec : POOL) {
			dec.set();
		}
	}
}
