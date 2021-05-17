package view.decorator;

import javax.swing.JPanel;

public abstract class PanelDecorator extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JPanel panel;
	
	public PanelDecorator(JPanel panel)
	{
		this.panel = panel;
	}
	
	public void setSize()
	{
		panel.setSize(0, 0);
	}
}
