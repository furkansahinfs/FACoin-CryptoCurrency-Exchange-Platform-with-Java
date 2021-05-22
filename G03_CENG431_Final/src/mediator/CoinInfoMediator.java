package mediator;

import java.util.ArrayList;
import java.util.List;

import controller.CoinInfoController;
import controller.Consumable;
import controller.IConsumable;
import enums.ECandleType;
import fileio.repository.UserRepository;
import model.User;
import service.CandleChartService;
import service.CoinInfoService;
import service.TransactionService;
import service.WalletServiceParam;
import view.CoinInfoView;
import view.color.ColorPalette;
import view.color.DarkTheme;
import view.decorator.DarkThemeDecorator;
import view.decorator.Decorator;
import view.decorator.TextDecorator;

public class CoinInfoMediator extends Consumable {

	private User user;
	private CoinInfoView view;
	private IConsumable controller;
	private String coinName;
	private String banknoteName;
	private String coinId;
	private String banknoteId;
	private CandleChartService service;
	private CoinInfoService coinInfoService;
	private Decorator textDecorator;

	private boolean isDayCandle = true;
	private boolean isHourCandle = false;

	public CoinInfoMediator(String title, User user) {
		this.user = user;
		coinInfoService = new CoinInfoService();
		setNamesAndIds(title);
		service = new CandleChartService(coinName, banknoteName);
		view = new CoinInfoView();
		new DarkThemeDecorator(view);
		textDecorator = new TextDecorator(view, coinName, banknoteName);
		UpdatePool.POOL.add(textDecorator);
		controller = new CoinInfoController(this);
		service.setViewChart(ECandleType.DAY, view);
		setFavoriteButtonColor();
	}

	public void back() {
		controller.supressNotUsed();
		view.setVisible(false);
		UpdatePool.POOL.remove(textDecorator);
		IConsumable mediator = new HomeMediator(user);
		mediator.supressNotUsed();
	}

	private void setNamesAndIds(String title) {
		String[] namesIds = coinInfoService.setNamesAndIds(title);
		this.coinName = namesIds[0];
		this.banknoteName = namesIds[1];
		this.coinId = namesIds[2];
		this.banknoteId = namesIds[3];
	}

	private void setFavoriteButtonColor() {
		ColorPalette palette = new ColorPalette(new DarkTheme());
		List<String> list = user.getFavorites().get(coinId);
		if (list == null) {
			list = new ArrayList<String>();
		}
		if (list.contains(banknoteId)) {
			view.setFavoriteColor(palette.FIRST_COLOR);
		} else {
			view.setFavoriteColor(palette.SECOND_COLOR);
		}
	}

	public void favorite() {
		List<String> str = user.getFavorites().get(coinId);
		ColorPalette palette = new ColorPalette(new DarkTheme());
		if (str == null) {
			view.setFavoriteColor(palette.FIRST_COLOR);
			List<String> list = new ArrayList<String>();
			list.add(banknoteId);
			user.getFavorites().put(coinId, list);
			(new UserRepository()).saveChanges();

		} else {
			if (!str.contains(banknoteId)) {
				view.setFavoriteColor(palette.FIRST_COLOR);
				str.add(banknoteId);
				(new UserRepository()).saveChanges();

			} else {
				view.setFavoriteColor(palette.SECOND_COLOR);
				List<String> list = user.getFavorites().get(coinId);
				list.remove(banknoteId);
				if (list.isEmpty()) {
					user.getFavorites().remove(coinId);
				}
				(new UserRepository()).saveChanges();
			}
		}

	}

	public CoinInfoView getView() {
		return view;
	}

	public void dayCandleChart() {
		if (!isDayCandle) {
			service.setViewChart(ECandleType.DAY, view);
			isDayCandle = true;
			isHourCandle = false;
		}
	}

	public void hourCandleChart() {
		if (!isHourCandle) {
			service.setViewChart(ECandleType.HOUR, view);
			isHourCandle = true;
			isDayCandle = false;
		}
	}

	public void sellCoin() {
		String[] coinQuantityAndValue = view.getSell();
		try {
			Double quantity = Double.valueOf(coinQuantityAndValue[0]);
			Double value = Double.valueOf(coinQuantityAndValue[1]);
			TransactionService transaction = new TransactionService(user);
			WalletServiceParam params = new WalletServiceParam(coinName, banknoteName, quantity, value, coinId,
					banknoteId);
			boolean result = transaction.sellCoin(params);
			if (!result) {
				view.showAlert("You do not have enough coin to sell");
			}
		} catch (Exception e) {
			view.showAlert("Please use \".\" in prices and quantities when using fractions");
		}

	}

	public void buyCoin() {
		String[] coinQuantityAndValue = view.getBuy();
		try {
			Double quantity = Double.valueOf(coinQuantityAndValue[0]);
			Double value = Double.valueOf(coinQuantityAndValue[1]);
			TransactionService transaction = new TransactionService(user);
			WalletServiceParam params = new WalletServiceParam(coinName, banknoteName, quantity, value, coinId,
					banknoteId);
			boolean result = transaction.buyCoin(params);
			if (!result) {
				view.showAlert("You do not have enough banknote");
			}
		} catch (Exception e) {
			view.showAlert("Please use \".\" in prices and quantities when using fractions");
		}
	}

}