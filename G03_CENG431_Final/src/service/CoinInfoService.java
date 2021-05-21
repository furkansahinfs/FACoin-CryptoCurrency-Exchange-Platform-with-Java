package service;

import fileio.repository.BanknoteRepository;
import fileio.repository.CoinRepository;
import fileio.repository.DatabaseResult;
import fileio.repository.IRestrictedRepository;
import model.AbstractEntity;
import model.Currency;

public class CoinInfoService {
	private IRestrictedRepository<Currency> coinRepository;
	private IRestrictedRepository<Currency> banknoteRepository;
	public CoinInfoService() {
		coinRepository = new CoinRepository();
		banknoteRepository = new BanknoteRepository();
	}
	
	public String[] setNamesAndIds(String title) {
		String coinName,banknoteName,coinId,banknoteId;
		String[] names = initCurrencyNames(title);
		coinName = names[0];
		banknoteName = names[1];
		String[] ids = initCoinIds(coinName,banknoteName);
		coinId = ids[0];
		banknoteId = ids[1];
		String[] namesAndIds = {coinName,banknoteName,coinId,banknoteId};
		return namesAndIds;
	}
	
	private String[] initCoinIds(String coinName, String banknoteName) {
		DatabaseResult coinResult = coinRepository.getByName(coinName);
		String coinId = ((AbstractEntity)coinResult.getObject()).getId();
		
		DatabaseResult banknoteResult = banknoteRepository.getByName(banknoteName);
		String banknoteId = ((AbstractEntity)banknoteResult.getObject()).getId();
		String ids[] = {coinId,banknoteId};
		return ids;
		
	}
	
	private String[] initCurrencyNames(String title) {
		String[] tradingPair = title.split("/");
		String coinName = tradingPair[0].replaceAll(" ","");
		String banknoteName = tradingPair[1].replaceAll(" ","");
		String[] names = {coinName, banknoteName};
		return names;
	}
}
