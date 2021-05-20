package view;

import javax.swing.JPanel;import view.list.List;

public abstract class AppView extends JPanel implements IColorable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AppView() {
	}


	
	public abstract void setList(List list);
	public abstract List getList();
}