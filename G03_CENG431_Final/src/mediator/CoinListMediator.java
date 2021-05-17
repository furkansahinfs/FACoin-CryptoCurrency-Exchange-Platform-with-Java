package mediator;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import fileio.repository.BanknoteRepository;
import fileio.repository.CoinRepository;
import fileio.repository.IRestrictedRepository;
import model.Currency;


public class CoinListMediator {

	private IRestrictedRepository<Currency> coinRepository;
	private IRestrictedRepository<Currency> banknoteRepository;

	public CoinListMediator() {
		coinRepository = new CoinRepository();
		banknoteRepository = new BanknoteRepository();
	}

	private List<LabelInfo> getLabel(Currency currency) {
		List<LabelInfo> labels = new ArrayList<LabelInfo>();
		final Iterator<Currency> banknoteIterator = banknoteRepository.getAll();
		Currency banknote = null;
		
		while (banknoteIterator.hasNext()) {
			
			banknote = banknoteIterator.next();
			Float oldValue = currency.getOldValue().get(banknote.getName());
			
			Float value = currency.getValue().get(banknote.getName());
			if(value==null)
				value = (float) 0;
			if(oldValue == null)
			{
				oldValue =value;
			}
			Float howMuch;
			if(value!=0)
			{
				howMuch = (value * 100 / oldValue) - 100;
			}
			else
			{
				howMuch = (float) 1;
			}
			

			Color result = null;

			if (howMuch > 0)
				result = Color.GREEN;
			else
				result = Color.RED;

			LabelInfo newLabel = new LabelInfo(result, howMuch, currency.getName(), value, banknote.name);
			labels.add(newLabel);

		}
		return labels;

	}

	public DefaultListModel<JLabel> getList() {
		DefaultListModel<JLabel> list = new DefaultListModel<JLabel>();
		final Iterator<Currency> newIterator = coinRepository.getAll();
		Currency currency = null;
		while (newIterator.hasNext()) {
			currency = newIterator.next();
			List<LabelInfo> labels = getLabel(currency);

			setLabels(labels, list);
		}

		return list;
	}

	public void setLabels(List<LabelInfo> labels, DefaultListModel<JLabel> listModel) {
		int index=0;
		for (LabelInfo labelInfo : labels) {
			JLabel label = createJLabel(labelInfo);
			System.out.println("CoinListMediator : " +labelInfo.toString());
			listModel.add(index, label);
			index++;
		}
	}

	public JLabel createJLabel(LabelInfo label) {
		JLabel jLabel = new JLabel();
		jLabel.setForeground(label.color);

		String text = label.coinName + "/" + label.banknote + " : " + label.value + " (" + label.percent + "%)";
		jLabel.setText(text);
		return jLabel;
	}

	class LabelInfo {
		private Color color;
		private Float percent;
		private String coinName;
		private Float value;
		private String banknote;

		public LabelInfo(Color color, Float percent, String name, Float value, String banknote) {
			this.color = color;
			this.percent = percent;
			this.coinName = name;
			this.value = value;
			this.banknote = banknote;
		}
		
		public String toString(){
			return this.coinName + "/" + this.banknote + " : " + this.value + " (" + this.percent + "%)";
		}
	}

}
