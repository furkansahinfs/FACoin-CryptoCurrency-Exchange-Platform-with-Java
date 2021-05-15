package fileio.repository;

import java.util.Dictionary;
import java.util.Enumeration;

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
		UpdateData[] datas = {};
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

	private void updateCoinValues(UpdateData[] values) {
		for (UpdateData data : values) {
			DatabaseResult dr = coins.getByName(data.name);
			if (dr.getObject() == null)
				continue;
			Currency coin = (Currency) dr.getObject();
			setValues(coin, data.values);
		}
	}

	private void setValues(Currency coin, Dictionary<String, Float> values) {
		Enumeration<String> iterator = values.keys();
		String key = "";
		while (iterator.hasMoreElements()) {
			key = iterator.nextElement();
			Float value = values.get(key);
			coin.addValue(key, value);
		}
	}

}