package mediator;

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
		service = new CandleChartService(coinName,banknoteName);
		view = new CoinInfoView();
		new DarkThemeDecorator(view);
		textDecorator = new TextDecorator(view,coinName,banknoteName);
		UpdatePool.POOL.add(textDecorator);
		controller = new CoinInfoController(this);
		service.setViewChart(ECandleType.DAY,view);
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
	
	
	
	private void setFavoriteButtonColor()
	{
	
		String str = user.getFavorites().get(coinId);
		ColorPalette palette = new ColorPalette(new DarkTheme());
		if(str == null)
		{
			view.setFavoriteColor(palette.SECOND_COLOR);
		}
		else
		{
			view.setFavoriteColor(palette.FIRST_COLOR);
		}
	}
	
	public void favorite() {
		String str = user.getFavorites().get(coinId);
		ColorPalette palette = new ColorPalette(new DarkTheme());
		if(str==null)
		{
			view.setFavoriteColor(palette.FIRST_COLOR);
			user.getFavorites().put(coinId, banknoteId);
			(new UserRepository()).saveChanges();
			
		}
		else
		{
			view.setFavoriteColor(palette.SECOND_COLOR);
			user.getFavorites().remove(coinId);
			(new UserRepository()).saveChanges();
		}
		
	}

	public CoinInfoView getView() {
		return view;
	}
		
	public void dayCandleChart() {
		if(!isDayCandle){
			service.setViewChart(ECandleType.DAY,view);
			isDayCandle = true;
			isHourCandle = false;
		}
	}

	public void hourCandleChart() {
		if(!isHourCandle){
			service.setViewChart(ECandleType.HOUR,view);
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
			WalletServiceParam params = new WalletServiceParam(coinName,banknoteName,quantity,value,coinId,banknoteId);
			transaction.sellCoin(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void buyCoin() {		
		String[] coinQuantityAndValue = view.getBuy();
		try {
			Double quantity = Double.valueOf(coinQuantityAndValue[0]);
			Double value = Double.valueOf(coinQuantityAndValue[1]);
			TransactionService transaction = new TransactionService(user);
			WalletServiceParam params = new WalletServiceParam(coinName,banknoteName,quantity,value,coinId,banknoteId);
			transaction.buyCoin(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	

}