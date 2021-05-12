package service;

import fileio.CurrencyRepository;
import fileio.DatabaseResult;

public class HomeService {

	private final CurrencyRepository currencies;

	public HomeService() {
		currencies = new CurrencyRepository();
	}

	
}