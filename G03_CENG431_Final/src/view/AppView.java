package view;

import javax.swing.JPanel;
import view.color.ColorPalette;
import view.list.List;

public abstract class AppView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AppView() {
	}


	public abstract void setPalette(ColorPalette palette);
	public abstract void setList(List list);

}
