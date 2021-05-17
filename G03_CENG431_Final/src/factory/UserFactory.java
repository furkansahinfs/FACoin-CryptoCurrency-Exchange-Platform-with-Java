package factory;

import factory.validator.UserValidator;
import factory.validator.ValidationResult;
import fileio.repository.BankWalletRepository;
import fileio.repository.CryptoWalletRepository;
import fileio.repository.DatabaseResult;
import model.User;
import model.Wallet;

public class UserFactory {

	public UserFactory() {

	}

	public User createUser(String userId, String userName, String password, String cryptoWalletId,
			String bankWalletId) {
		User userResult = null;
		ValidationResult isUserOK = UserValidator.validateUser(userName, password, userId);
		if (isUserOK.isValid) {

			DatabaseResult resultCryptoWallet = (new CryptoWalletRepository()).getById(cryptoWalletId);
			DatabaseResult resultBankWallet = (new BankWalletRepository()).getById(bankWalletId);
			Object cryptoWallet = resultCryptoWallet.getObject();
			Object bankWallet = resultBankWallet.getObject();

			if (cryptoWallet != null && bankWallet != null) {
				userResult = new User(userId, userName, password, (Wallet) cryptoWallet, (Wallet) bankWallet);
			}
		}

		return userResult;
	}
}
