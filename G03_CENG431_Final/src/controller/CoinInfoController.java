package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mediator.CoinInfoMediator;
/**
 * This class handles home screen requests
 */
public class CoinInfoController extends Consumable {

	private CoinInfoMediator mediator;
	
	public CoinInfoController(CoinInfoMediator mediator) {
		this.mediator = mediator;
		mediator.getView().addBackButtonListener(new BackButtonListener());
		mediator.getView().addDayCandleButtonListener(new DayCandleButtonListener());
		mediator.getView().addHourCandleButtonListener(new HourCandleButtonListener());
		
	}
	
	class BackButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mediator.back();
		}

	}

	class DayCandleButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mediator.dayCandleChart();
		}

	}


	class HourCandleButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mediator.hourCandleChart();
		}

	}
	
	class BuyButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mediator.hourCandleChart();
		}

	}
	
	class SellButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mediator.hourCandleChart();
		}

	}
}
