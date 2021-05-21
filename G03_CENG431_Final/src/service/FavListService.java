package service;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;

import fileio.repository.BanknoteRepository;
import fileio.repository.CoinRepository;
import fileio.repository.DatabaseResult;
import fileio.repository.IRestrictedRepository;
import fileio.repository.UserRepository;
import mediator.CoinListMediator;
import view.list.CoinList;
import view.list.List;
import model.Currency;
import model.User;
import java.util.Dictionary;
import java.util.Enumeration;

public class FavListService {

	private final String userId;
	private final IRestrictedRepository<User> users;
	private final IRestrictedRepository<Currency> coins;
	private final IRestrictedRepository<Currency> banknotes;
	
	public FavListService(String userId) {
		this.userId = userId;
		users = new UserRepository();
		coins = new CoinRepository();
		banknotes = new BanknoteRepository();
	}
	
	public List getList() {
		List list = null;
		DatabaseResult dr = users.getById(userId);
		if(dr.getObject()==null)
		{
			System.out.println("user null");
			return list;
		}
				
		list = getFavorites((User) dr.getObject());
		return list;
	}
	
	public List getFavorites(User user)
	{
		CoinListMediator mediator = new CoinListMediator();
		DefaultListModel<JLabel> listModel = mediator.getList();
		DefaultListModel<JLabel> favoritesModel = new DefaultListModel<JLabel>();
		Dictionary<String,String> favorites = user.getFavorites();
		
		Enumeration<String> keys = favorites.keys();
 
        // iterate using enumeration object
        while(keys.hasMoreElements()) {
 
            String coinId = keys.nextElement();
			String banknoteId = favorites.get(coinId);
			
			DatabaseResult coinResult = coins.getById(coinId);
			DatabaseResult banknoteResult = banknotes.getById(banknoteId);
			
			String coin = ((Currency) coinResult.getObject()).getName();
			String banknote = ((Currency) banknoteResult.getObject()).getName();			
			JLabel label = findLabel(listModel,coin,banknote);
			
			if(label != null)
			{
				int index = favoritesModel.size();
				favoritesModel.add(index, label);
			}
			
           	
        }

		return new CoinList(favoritesModel);
 
	}
	
	private JLabel findLabel(DefaultListModel<JLabel> listModel, String coin, String banknote)
	{
		JLabel returnedLabel = null;
		JLabel label = null;
		for(int i=0;i<listModel.size();i++)
		{
			label = listModel.get(i);
			String text = label.getText();
			if(text.contains(coin+"/"+banknote))
			{
				returnedLabel = label;
				break;
			}
		}
		return returnedLabel;
		
	}
	
	
		
	
	
}
