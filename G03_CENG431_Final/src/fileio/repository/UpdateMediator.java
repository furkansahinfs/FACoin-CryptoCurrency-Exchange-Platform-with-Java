package fileio.repository;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.List;

import exception.HttpRequestException;
import httpio.HttpIO;
import httpio.IHttpIO;
import model.Currency;
import settings.AppSettings;

public class UpdateMediator {
	private IHttpIO httpIO;
	private IRestrictedRepository<Currency> coins;

	public UpdateMediator() {
		httpIO = new HttpIO();
		coins = new CoinRepository();
	}

	public void updateValues() {
		String getUrl = setEndpoint();
		List<UpdateData> datas = new ArrayList<UpdateData>();
		try {
			datas = httpIO.getValues(getUrl);
		} catch (HttpRequestException e) {
			return;
		}
		updateCoinValues(datas);
	}

	private String setEndpoint() {
		String coins = BaseRepository.coins().toString(0);
		String banknotes = BaseRepository.banknotes().toString(0);
		String url = "https://min-api.cryptocompare.com/data/pricemulti?fsyms=" + coins + "&tsyms=" + banknotes
				+ "&api_key=" + AppSettings.CRYPTO_API_KEY;
		return url;
	}

	private void updateCoinValues(List<UpdateData> values) {
		for (UpdateData data : values) {
			
			DatabaseResult dr = coins.getByName(data.name);
			if (dr.getObject() == null)
				continue;
			Currency coin = (Currency) dr.getObject();
			System.out.println("UpdateMediator : "+ data.getValues());
			setValues(coin, data.values);
		}
	}

	private void setValues(Currency coin, Dictionary<String, Float> values) {
		Enumeration<String> iterator = values.keys();
		String key = "";
		while (iterator.hasMoreElements()) {
			key = iterator.nextElement();
			Float value = values.get(key);
			Float oldValue = (float) 0;
			oldValue = coin.addValue(key, value);
			if(oldValue==null)
				oldValue = value;
			coin.addOldValue(key, oldValue);
		}
	}

}