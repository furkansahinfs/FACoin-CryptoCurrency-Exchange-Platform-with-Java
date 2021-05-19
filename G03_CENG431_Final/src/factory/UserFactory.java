package factory;

import factory.validator.UserValidator;
import factory.validator.ValidationResult;
import fileio.repository.BankWalletRepository;
import fileio.repository.BanknoteRepository;
import fileio.repository.CoinRepository;
import fileio.repository.CryptoWalletRepository;
import fileio.repository.DatabaseResult;
import model.User;
import model.Wallet;
import java.util.Dictionary;
import java.util.Hashtable;

public class UserFactory {

	public UserFactory() {

	}

	public User createUser(String userId, String userName, String password, String cryptoWalletId,
			String bankWalletId, String favorites) {
		User userResult = null;
		ValidationResult isUserOK = UserValidator.validateUser(userName, password, userId);
		if (isUserOK.isValid) {

			DatabaseResult resultCryptoWallet = (new CryptoWalletRepository()).getById(cryptoWalletId);
			DatabaseResult resultBankWallet = (new BankWalletRepository()).getById(bankWalletId);
			Object cryptoWallet = resultCryptoWallet.getObject();
			Object bankWallet = resultBankWallet.getObject();

			if (cryptoWallet != null && bankWallet != null) {
				Dictionary<String,String> favoritesDictionary = createDictionary(favorites);
				userResult = new User(userId, userName, password, (Wallet) cryptoWallet, (Wallet) bankWallet, favoritesDictionary);
			}
		}

		return userResult;
	}
	
	private Dictionary<String,String> createDictionary(String favorites)
	{
		Dictionary<String,String> favoritesDictionary = new Hashtable<String,String>();
		if(favorites.isEmpty())
			return favoritesDictionary;
		String[] splittedFavorites = favorites.split(",");
		for (String tradePair : splittedFavorites) {
			String[] pair = tradePair.split("-");	
			DatabaseResult resultOfCoin = (new CoinRepository()).getById(pair[0]);
			DatabaseResult resultOfBanknote = (new BanknoteRepository()).getById(pair[1]);
			Object coin = resultOfCoin.getObject();
			Object banknote = resultOfBanknote.getObject();

			if (coin != null && banknote != null) {
				favoritesDictionary.put(pair[0], pair[1]);
			}	
		}
		return favoritesDictionary;
	}
}
