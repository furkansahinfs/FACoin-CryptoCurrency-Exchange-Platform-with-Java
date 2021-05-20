package mediator;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import fileio.repository.BankWalletRepository;
import fileio.repository.BanknoteRepository;
import fileio.repository.CryptoWalletRepository;
import fileio.repository.DatabaseResult;
import fileio.repository.IRestrictedRepository;
import model.BankWallet;
import model.CryptoWallet;
import model.Currency;
import model.WalletEntity;
import view.LabelInfo;

public class WalletListMediator {

	private IRestrictedRepository<Currency> banknoteRepository;
	private String bankId;
	private String cryptoId;

	public WalletListMediator(String cryptoId, String bankId) {
		this.bankId = bankId;
		this.cryptoId = cryptoId;
		banknoteRepository = new BanknoteRepository();
	}

	private List<LabelInfo> getCryptoLabel(WalletEntity walletEntity) {

		List<LabelInfo> labels = new ArrayList<LabelInfo>();
		final Iterator<Currency> banknoteIterator = banknoteRepository.getAll();
		Currency banknote = null;
		Currency currency = (Currency) walletEntity.getCurrency();
		double quantity = walletEntity.getQuantity();
		while (banknoteIterator.hasNext()) {

			banknote = banknoteIterator.next();
			Double value = currency.getValue().get(banknote.getName()) * quantity;
			if (value.equals(null)) {
				value = (double) 0;
			}
			LabelInfo newLabel = new LabelInfo(Color.CYAN, quantity, currency.getName(), value, banknote.name);
			labels.add(newLabel);
		}
		return labels;
	}
	
	private List<LabelInfo> getBanknoteLabel(WalletEntity walletEntity) {

		List<LabelInfo> labels = new ArrayList<LabelInfo>();
		Currency currency = (Currency) walletEntity.getCurrency();
		double quantity = walletEntity.getQuantity();
		
		Double value = 1 * quantity;
		if (value.equals(null)) {
			value = (double) 0;
		}
		LabelInfo newLabel = new LabelInfo(Color.CYAN, quantity, currency.getName(), value, "");
		labels.add(newLabel);
		return labels;
	}

	public DefaultListModel<JLabel> getCryptoWalletList() {
		DefaultListModel<JLabel> list = new DefaultListModel<JLabel>();
		DatabaseResult walletResult = (new CryptoWalletRepository()).getById(cryptoId);
		if (walletResult.getObject() != null) {
		
			CryptoWallet wallet = (CryptoWallet) walletResult.getObject();
			for (WalletEntity walletEntity : wallet.getEntities().getContainer()) {
				List<LabelInfo> labels = getCryptoLabel(walletEntity);
				setLabels(labels, list);
			}
		}

		return list;
	}

	public DefaultListModel<JLabel> getBankWalletList() {
		DefaultListModel<JLabel> list = new DefaultListModel<JLabel>();
		DatabaseResult walletResult = (new BankWalletRepository()).getById(bankId);
		if (walletResult.getObject() != null) {
		
			BankWallet wallet = (BankWallet) walletResult.getObject();
			for (WalletEntity walletEntity : wallet.getEntities().getContainer()) {
				List<LabelInfo> labels = getBanknoteLabel(walletEntity);
				setLabels(labels, list);
			}
		}

		return list;
	}

	public void setLabels(List<LabelInfo> labels, DefaultListModel<JLabel> listModel) {
		int index = 0;
		for (LabelInfo labelInfo : labels) {
			JLabel label = createJLabel(labelInfo);
			listModel.add(index, label);
			index++;
		}
	}

	public JLabel createJLabel(LabelInfo label) {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(4);
		String text = label.coinName + "/" + label.banknote + " : \tQuantity : " + label.percent + " - Value : " + df.format(label.value);
		JLabel jLabel = new JLabel(text);
		jLabel.setForeground(label.color);
		return jLabel;
	}

}
