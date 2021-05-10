package factory;

import java.util.Dictionary;

import model.Wallet;
import model.WalletEntity;
import storage.IContainer;
import storage.WalletEntityContainer;

public class CryptoWalletFactory extends WalletFactory{

	@Override
	public Wallet createWallet(String id, Dictionary<String, String> params) {
		Wallet walletResult = null;
		
		IContainer<WalletEntity> entities = new WalletEntityContainer();
	
		// TODO foreach dön, validator ekle, factory düzenle, HTTP get
		walletResult = new Wallet(id,entities);
		return walletResult;	
	}
	
}