package factory;

import java.util.Dictionary;

import model.Wallet;
import model.WalletEntity;

public abstract class WalletFactory {

	public WalletFactory(){}
	public abstract Wallet createWallet(String id, Dictionary<String,String> params);
	protected abstract WalletEntity createWalletEntity(String currencyId, String quantity);

}
