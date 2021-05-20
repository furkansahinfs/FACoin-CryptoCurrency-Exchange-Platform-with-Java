package view.decorator;

import javax.swing.JLabel;
import service.JLabelService;
import view.AppView;

public class TextDecorator extends Decorator{

	private AppView view ;
	private String coinName;
	private String banknoteName;
	public TextDecorator(AppView view, String coinName, String banknoteName) {
		this.view = view;
		this.coinName = coinName;
		this.banknoteName = banknoteName;
		set();
	}

	@Override
	public void set() {
		update();		
	}

	
	public void update()
	{
		JLabelService service = new JLabelService();
		JLabel label = service.getCoinLabel(coinName, banknoteName);
		this.view.setLabel(label);
	}

}
