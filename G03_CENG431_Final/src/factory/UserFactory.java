package factory;

import factory.validator.UserValidator;
import factory.validator.ValidationResult;
import fileio.DatabaseResult;
import fileio.WalletRepository;
import model.User;
import model.Wallet;

public class UserFactory {

	public UserFactory() {

	}

	public User createUser(String userId, String userName, String password, String cryptoWalletId, String bankWalletId) {
		User userResult = null;
		ValidationResult isUserOK = UserValidator.validateUser(userName, password, userId);

		if (isUserOK.isValid) {
			DatabaseResult resultCryptoWallet = (new WalletRepository()).getCryptoWalletById(cryptoWalletId);
			DatabaseResult resultBankWallet = (new WalletRepository()).getBankWalletById(bankWalletId);
			Object cryptoWallet = resultCryptoWallet.getObject();
			Object bankWallet = resultBankWallet.getObject();
			if (cryptoWallet != null && bankWallet != null) {
				userResult = new User(userId, userName, password, (Wallet) cryptoWallet, (Wallet) bankWallet);
			}
		}

		return userResult;
	}
}
