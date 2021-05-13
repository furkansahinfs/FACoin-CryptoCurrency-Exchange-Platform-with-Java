package service;

import fileio.repository.CoinRepository;
import fileio.repository.DatabaseResult;

public class HomeService {

	private final CoinRepository currencies;

	public HomeService() {
		currencies = new CoinRepository();
	}

	
}