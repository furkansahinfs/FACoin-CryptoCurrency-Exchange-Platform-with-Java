package service;

import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.DefaultHighLowDataset;

import enums.ECandleType;
import httpio.repository.DayCandleRepository;
import httpio.repository.HourCandleRepository;
import model.Candle;
import storage.IContainer;
import view.CoinInfoView;

public class CandleChartService {
	
	private DayCandleRepository dayCandleRepository;
	private HourCandleRepository hourCandleRepository;
	private String coinName;
	private String banknoteName;
	public CandleChartService(String coinName, String banknoteName) {
		dayCandleRepository = new DayCandleRepository();
		hourCandleRepository = new HourCandleRepository();
		this.banknoteName = banknoteName;
		this.coinName = coinName;
	}

	
	public void setViewChart(ECandleType type, CoinInfoView view) {
		DefaultHighLowDataset dataset = createOHLCDataset(type);
		final JFreeChart chart = createChart(dataset);
		view.setChart(chart);
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
