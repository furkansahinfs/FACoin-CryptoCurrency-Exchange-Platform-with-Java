package service;

import javax.swing.JLabel;
import fileio.repository.CoinRepository;
import fileio.repository.DatabaseResult;
import model.ICurrency;

public class JLabelService {
	private final CoinRepository currencies;

	public JLabelService() {
		currencies = new CoinRepository();
	}
	
	public JLabel getCoinLabel(String coinName, String banknoteName)
	{
		JLabel label = new JLabel();
		DatabaseResult coinResult = currencies.getByName(coinName);
		if(coinResult.getObject() != null)
		{
			ICurrency coin = (ICurrency) coinResult.getObject();			
			label.setText(coinName + "/" + banknoteName + "\n" + coin.getValue().get(banknoteName));			
		}
		return label;	
	}
}
	