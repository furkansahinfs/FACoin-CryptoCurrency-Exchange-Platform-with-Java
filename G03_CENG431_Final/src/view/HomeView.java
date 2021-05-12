package view;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.User;
import service.CoinPrintService;

import java.awt.event.MouseListener;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.JList;

/**
 * The home view is a view which user sees other users' collections. And he/she
 * can reach the following view, follower view , discover users view, collection
 * view, top rated things view using buttons
 *
 */
public class HomeView extends JPanel {

	private static final long serialVersionUID = 7713286782412153047L;
	private JList<String> coinList; // JList of followed users' collections with outfits.
	private JScrollPane posts; // scroll pane which contains list of followed users' collections with outfits.

	/**
	 * Create the frame.
	 */
	public HomeView() {

		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);


		posts = new JScrollPane();
		posts.setBounds(180, 40, 320, 300);

		// HomeController initialises list using update at the start
		// and in the continuation. Default it is empty.
		coinList = new JList<String>();
		posts.setViewportView(coinList);
		add(posts);

	}


	/**
	 * The function helps for detecting of selecting a coin using listener
	 * 
	 * @param listener
	 */
	public void addSelectCoinListener(MouseListener listener) {
		coinList.addMouseListener(listener);
	}


	/**
	 * The function returns the selected coin.
	 * 
	 * @param String of selected user name
	 */
	public String getListSelected() {
		return coinList.getSelectedValue();
	}
	
	
	/**
	 * If a user is gotten in the update method, set the followed users' collections
	 * list according the gotten user's followed users
	 * 
	 * @param user
	 */
	private void updateScroll(User user) {
		final var listModel = (new CoinPrintService(user)).getScroolString();
		coinList.setModel((ListModel<String>) listModel);
	}


	
}
