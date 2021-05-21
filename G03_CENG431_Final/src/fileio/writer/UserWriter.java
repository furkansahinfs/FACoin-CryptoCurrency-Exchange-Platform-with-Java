package fileio.writer;

import model.User;
import storage.IContainer;

public class UserWriter {

	protected UserWriter() {
		
	}
	
	protected String writeUsersXml(IContainer<User> users) {
		String result = "<?xml version = \"1.0\"?>\n<users>\n";
		for(User user: users) {
			result+=convertUserToXmlString(user);
		}
		result+="</users>";
		return result;
	}
	
	private String convertUserToXmlString(User user) {
		return "    <user id=\""+user.getId()+"\">\n\t"+
		"\t<username>"+user.getUserName()+"</username>\n\t"+
		"\t<password>"+user.getPassword()+"</password>\n\t"+
		"\t<crypto_wallet>"+user.getCryptoWallet().getId()+"</crypto_wallet>\n\t"+
		"\t<bank_wallet>"+user.getBankWallet().getId()+"</bank_wallet>\n\t"+
		"\t"+FavoritesWriter.writeFavoriteTradingPairsXml(user.getFavorites())+"\n\t"+
		"\t"+UserTransactionsWriter.writeUserTransactionsXml(user.getTransactions())+
		"    \n\t</user>\n";
	}
}