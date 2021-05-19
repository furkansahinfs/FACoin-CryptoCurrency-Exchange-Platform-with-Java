package mediator;

import java.util.Date;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.DefaultHighLowDataset;
import controller.CoinInfoController;
import httpio.repository.DayCandleRepository;
import model.Candle;
import model.User;
import storage.IContainer;
import view.CoinInfoView;
import view.decorator.DarkThemeDecorator;
import view.decorator.JListDecorator;
import view.decorator.ThemeDecorator;
import storage.IContainer;

public class CoinInfoMediator {

	private User user;
	private CoinInfoView view;
	private CoinInfoController controller;
	private JListDecorator decorator;
	private String coinName;
	private String banknoteName;

	public CoinInfoMediator(String title) {
		initCurrencyNames(title);
		view = new CoinInfoView();
		ThemeDecorator dec = new DarkThemeDecorator(view);
		controller = new CoinInfoController(this);
		setViewChart();

	}

	private void initCurrencyNames(String title) {
		String[] tradingPair = title.split("/");
		coinName = tradingPair[0].replaceAll(" ","");
		banknoteName = tradingPair[1].replaceAll(" ","");
		
	}

	public CoinInfoView getView() {
		return view;
	}
	

	public void setViewChart() {
		DefaultHighLowDataset dataset = createOHLCDataset();
		final JFreeChart chart = createChart(dataset);
		view.setChart(chart);
	}

	private JFreeChart createChart(final DefaultHighLowDataset dataset) {
		final JFreeChart chart = ChartFactory.createCandlestickChart("", "", "", dataset, true);
		return chart;
	}

	private DefaultHighLowDataset createOHLCDataset() {
		
		DayCandleRepository repo = new DayCandleRepository();
		IContainer<Candle> dayCandles = repo.day_candles(coinName, banknoteName);
		int len = dayCandles.getLength();
		Date[] dates = new Date[len];
		double[] opens = new double[len];
		double[] highs = new double[len];
		double[] lows = new double[len];
		double[] closes = new double[len];
		double[] volumes = new double[len];

		int i = 0;
		for (Candle d : dayCandles.getContainer()) {
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