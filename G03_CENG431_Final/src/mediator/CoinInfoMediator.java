package mediator;
import controller.CoinInfoController;
import enums.ECandleType;
import fileio.repository.UserRepository;
import model.User;
import service.CandleChartService;
import service.CoinInfoService;
import view.CoinInfoView;
import view.color.ColorPalette;
import view.color.DarkTheme;
import view.decorator.DarkThemeDecorator;
import view.decorator.TextDecorator;
import view.decorator.ThemeDecorator;

public class CoinInfoMediator {

	private User user;
	private CoinInfoView view;
	private CoinInfoController controller;
	private String coinName;
	private String banknoteName;
	private String coinId;
	private String banknoteId;

	private CandleChartService service;
	private CoinInfoService coinInfoService;
	
	private boolean isDayCandle = true;
	private boolean isHourCandle = false;

	public CoinInfoMediator(String title, User user) {
		this.user = user;
		coinInfoService = new CoinInfoService();
		setNamesAndIds(title);
		service = new CandleChartService(coinName,banknoteName);
		view = new CoinInfoView();
		ThemeDecorator themeDecorator = new DarkThemeDecorator(view);
		TextDecorator textDecorator = new TextDecorator(view,coinName,banknoteName);
		UpdatePool.POOL.add(textDecorator);
		controller = new CoinInfoController(this);
		service.setViewChart(ECandleType.DAY,view);
		setFavoriteButtonColor();
	}
	

	public void back() {
		view.setVisible(false);
		UpdatePool.POOL.clear();
		HomeMediator mediator = new HomeMediator(user);
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
		// TODO Auto-generated method stub
		
	}

	public void buyCoin() {
		// TODO Auto-generated method stub
		
	}
	
	

}