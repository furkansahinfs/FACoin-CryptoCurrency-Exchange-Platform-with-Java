package view;

import javax.swing.JWindow;

public class AppWindow extends JWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8130256364738099887L;

	protected static Frame FRAME;
	
	public AppWindow() {
		FRAME = new Frame("FACoin");
		FRAME.setVisible(true);
	}
}
