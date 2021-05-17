package view.decorator;



import javax.swing.JPanel;

public class PopupDecorator extends PanelDecorator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PopupDecorator(JPanel panel) {
		super(panel);
		// TODO Auto-generated constructor stub
	}
	
	public void setSize()
	{
		panel.setSize(100,200); 
	}



}

