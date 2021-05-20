package service;

import fileio.repository.CoinRepository;

public class HomeService {

	private final CoinRepository currencies;

	public HomeService() {
		currencies = new CoinRepository();
	}

	
}