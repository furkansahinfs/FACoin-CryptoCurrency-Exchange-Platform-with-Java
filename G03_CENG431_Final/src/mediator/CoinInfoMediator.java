package mediator;

import java.util.Date;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.DefaultHighLowDataset;
import controller.CoinInfoController;
import enums.ECandleType;
import fileio.repository.UserRepository;
import fileio.repository.BanknoteRepository;
import fileio.repository.CoinRepository;
import fileio.repository.DatabaseResult;
import fileio.repository.IRestrictedRepository;
import httpio.repository.DayCandleRepository;
import httpio.repository.HourCandleRepository;
import model.AbstractEntity;
import model.Candle;
import model.Currency;
import model.User;
import storage.IContainer;
import view.CoinInfoView;
import view.color.ColorPalette;
import view.color.DarkTheme;
import view.decorator.DarkThemeDecorator;
import view.decorator.JListDecorator;
import view.decorator.TextDecorator;
import view.decorator.ThemeDecorator;

public class CoinInfoMediator {

	private User user;
	private CoinInfoView view;
	private CoinInfoController controller;
	private JListDecorator decorator;
	private String coinName;
	private String banknoteName;
	private String coinId;
	private String banknoteId;
	private IRestrictedRepository<Currency> coinRepository;
	private IRestrictedRepository<Currency> banknoteRepository;
	private DayCandleRepository dayCandleRepository;
	private HourCandleRepository hourCandleRepository;
	
	private boolean isDayCandle = true;
	private boolean isHourCandle = false;

	public CoinInfoMediator(String title, User user) {
		this.user = user;
		initCurrencyNames(title);
		view = new CoinInfoView();
		coinRepository = new CoinRepository();
		banknoteRepository = new BanknoteRepository();
		initCoinIds();
		ThemeDecorator themeDecorator = new DarkThemeDecorator(view);
		TextDecorator textDecorator = new TextDecorator(view,coinName,banknoteName);
		UpdatePool.POOL.add(textDecorator);
		dayCandleRepository = new DayCandleRepository();
		hourCandleRepository = new HourCandleRepository();
		controller = new CoinInfoController(this);
		setViewChart(ECandleType.DAY);
		setFavoriteButtonColor();
	}
	
	private void initCoinIds() {
		DatabaseResult coinResult = coinRepository.getByName(coinName);
		coinId = ((AbstractEntity)coinResult.getObject()).getId();
		
		DatabaseResult banknoteResult = banknoteRepository.getByName(banknoteName);
		banknoteId = ((AbstractEntity)banknoteResult.getObject()).getId();
	}

	public void back() {
		view.setVisible(false);
		UpdatePool.POOL.clear();
		HomeMediator mediator = new HomeMediator(user);
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

	private void initCurrencyNames(String title) {
		String[] tradingPair = title.split("/");
		coinName = tradingPair[0].replaceAll(" ","");
		banknoteName = tradingPair[1].replaceAll(" ","");
		
	}

	public CoinInfoView getView() {
		return view;
	}
	
	public void setViewChart(ECandleType type) {
		DefaultHighLowDataset dataset = createOHLCDataset(type);
		final JFreeChart chart = createChart(dataset);
		view.setChart(chart);
	}
	
	public void dayCandleChart() {
		if(!isDayCandle){
			setViewChart(ECandleType.DAY);
			isDayCandle = true;
			isHourCandle = false;
		}
	}

	public void hourCandleChart() {
		if(!isHourCandle){
			setViewChart(ECandleType.HOUR);
			isHourCandle = true;
			isDayCandle = false; 
		}
	}

	private JFreeChart createChart(final DefaultHighLowDataset dataset) {
		final JFreeChart chart = ChartFactory.createCandlestickChart("", "", "", dataset, true);
		return chart;
	}

	private DefaultHighLowDataset createOHLCDataset(ECandleType type) {
		
		IContainer<Candle> candles;
		if(type == ECandleType.DAY)
		{
			candles = dayCandleRepository.day_candles(coinName, banknoteName);
		}
		else
		{

			candles = hourCandleRepository.hour_candles(coinName, banknoteName);
		}
	
		
		int len = candles.getLength();
		Date[] dates = new Date[len];
		double[] opens = new double[len];
		double[] highs = new double[len];
		double[] lows = new double[len];
		double[] closes = new double[len];
		double[] volumes = new double[len];

		int i = 0;
		for (Candle d : candles.getContainer()) {
			dates[i] = d.getCandleDate();
			opens[i] = Double.valueOf(d.getOpen());
			highs[i] = Double.valueOf(d.getHigh());
			lows[i] = Double.valueOf(d.getLow());
			closes[i] = Double.valueOf(d.getClose());
			volumes[i] = Double.valueOf(d.getVolume());
			i++;
		}

		return new DefaultHighLowDataset(coinName +"/"+ banknoteName,
				dates, highs, lows, opens, closes, volumes);
	}
	
	

}