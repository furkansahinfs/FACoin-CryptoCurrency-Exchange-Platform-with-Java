package controller;

import mediator.CoinInfoMediator;
/**
 * This class handles home screen requests
 */
public class CoinInfoController extends Consumable {

	private CoinInfoMediator mediator;
	
	public CoinInfoController(CoinInfoMediator mediator) {
		this.mediator = mediator;
	}





}
