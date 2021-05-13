package factory;

import java.util.Dictionary;
import java.util.Enumeration;

import factory.validator.BankWalletValidator;
import factory.validator.ValidationResult;
import model.BankWallet;
import model.Wallet;
import model.WalletEntity;
import storage.IContainer;
import storage.WalletEntityContainer;

public class BankWalletFactory extends WalletFactory {

	@Override
	public Wallet createWallet(String id, Dictionary<String, String> params) {
		String walletId = id;
		Wallet walletResult = null;
		ValidationResult vr = BankWalletValidator.validateBankWallet(walletId);
		if (!vr.isValid) {
			walletId = vr.messages;
		}
		boolean isNotEligible = false;
		if (params.isEmpty())
			return walletResult;
		IContainer<WalletEntity> entities = new WalletEntityContainer();
		Enumeration<String> keys = params.keys();
		String key = "";
		while (keys.hasMoreElements()) {
			key = keys.nextElement();
			WalletEntity result = super.createWalletEntity(key, params.get(key));
			if (result == null) {
				isNotEligible = true;
				break;
			}

			if (!BankWalletValidator.isEntityBanknote(result)) {
				isNotEligible = true;
				break;
			}

			entities.add(result);
		}
		if (isNotEligible)
			return walletResult;

		walletResult = new BankWallet(walletId, entities);
		return walletResult;
	}

}