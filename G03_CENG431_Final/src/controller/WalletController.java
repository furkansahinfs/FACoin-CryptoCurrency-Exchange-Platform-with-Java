package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import exception.HttpRequestException;
import mediator.WalletMediator;
/**
 * This class handles home screen requests
 */
public class WalletController extends Consumable {

	private WalletMediator mediator;
	
	public WalletController(WalletMediator mediator) {
		this.mediator = mediator;
		mediator.getView().addSelectCoinListener((new SelectCoinListener()));
		mediator.getView().addBackButtonListener(new BackButtonListener());
		mediator.getView().addBankButtonListener(new BankButtonListener());
		mediator.getView().addCryptoButtonListener(new CryptoButtonListener());
	}


	// by this class user can select one of the outfits
	class SelectCoinListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() > 1) {
				try {
					mediator.getSelectedCoinView();
				} catch (HttpRequestException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
	
	class BackButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mediator.back();
		}

	}
	
	class BankButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mediator.bankView();
		}

	}	

	class CryptoButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mediator.cryptoView();
		}

	}
}
