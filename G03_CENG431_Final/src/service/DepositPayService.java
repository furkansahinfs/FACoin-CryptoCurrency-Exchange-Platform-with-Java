package service;

import java.util.Iterator;

import fileio.repository.BanknoteRepository;
import fileio.repository.DatabaseResult;
import fileio.repository.IRestrictedRepository;
import model.Currency;
import model.Wallet;
import view.WalletView;

public class DepositPayService {

	private WalletService service;
	private IRestrictedRepository<Currency> banknotes;
	
	public DepositPayService() {
		service = new WalletService();
		banknotes = new BanknoteRepository();
	}

	public void setComboBoxValues(WalletView view) {
		Iterator<Currency> banknotesIT = banknotes.getAll();
		Currency banknote = null;
		String names = "";
		while(banknotesIT.hasNext()) {
			banknote = banknotesIT.next();
			names+=banknote.getName()+",";
		}
		if (names.endsWith(",")) { // if ends with , ignore it
			names = names.substring(0, names.length() - 1);
		}
		view.setComboBox(names.split(","));
	}

	public void processDepositRequest(Wallet bankWallet, String banknoteName, String quantity) {
		Double banknoteQuantity = 0.0;
		try {
			banknoteQuantity = Double.valueOf(quantity);
		}catch(NumberFormatException e) {
			return;
		}
		DatabaseResult drResult = banknotes.getByName(banknoteName);
		if(drResult.getObject()==null) {
			return;
		}
		Currency banknote = (Currency) drResult.getObject();
		service.setBankWalletQuantity(bankWallet, banknote, banknoteQuantity);
	}
}
