package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import service.HomeService;
import view.HomeView;
/**
 * This class handles home screen requests
 */
public class HomeController {

	public HomeView view;
	private HomeService homeService; // user set to be null

	public HomeController(HomeView view) {
		this.homeService = new HomeService(); // to show up outfits
		this.view = view;
		this.view.addSelectCoinListener((new SelectCoinListener()));
	}


	// by this class user can select one of the outfits
	class SelectCoinListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() > 1) {
				String selectedItemName = ((HomeView) view).getListSelected();
				System.out.println(selectedItemName);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	}


}
