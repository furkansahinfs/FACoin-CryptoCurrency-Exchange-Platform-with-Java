package view;

import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.xy.DefaultHighLowDataset;

import fileio.repository.BaseRepository;
import httpio.repository.DayCandleRepository;
import model.Candle;
import model.DayCandle;

public class CoinInfoView extends ApplicationFrame{
	//return new DefaultHighLowDataset("deneme", dates, highs, lows, opens, closes, volumes);
	public CoinInfoView(final String title) {
		super(title);
		final DefaultHighLowDataset dataset = createOHLCDataset();

		final JFreeChart chart = createChart(dataset);
		chart.getXYPlot().zoom(HEIGHT);
		chart.getXYPlot().setOrientation(PlotOrientation.VERTICAL);

		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));

		setContentPane(chartPanel);
	}

	private JFreeChart createChart(final DefaultHighLowDataset dataset) {
		final JFreeChart chart = ChartFactory.createCandlestickChart("Candlestick Demo", "Time", "Value", dataset,
				true);
		return chart;
	}

	private static DefaultHighLowDataset createOHLCDataset() {

		DayCandleRepository repo = new DayCandleRepository();
		int len = repo.day_candles().getLength();
		Date[] dates = new Date[len];
		double[] opens = new double[len];
		double[] highs = new double[len];
		double[] lows = new double[len];
		double[] closes = new double[len];
		double[] volumes = new double[len];
		
		int i=0;
		for (Candle d : repo.day_candles().getContainer()) {
			dates[i] = d.getCandleDate();
			opens[i] = Double.valueOf(d.getOpen());
			highs[i] = Double.valueOf(d.getHigh());
			lows[i] = Double.valueOf(d.getLow());
			closes[i] = Double.valueOf(d.getClose());
			volumes[i] = Double.valueOf(d.getVolume());
			i++;
		}
		

		return new DefaultHighLowDataset("deneme", dates, highs, lows, opens, closes, volumes);
	}
}

/*
 * import java.sql.Date;
 * 
 * 
 * import org.jfree.chart.ChartFactory; import org.jfree.chart.ChartPanel;
 * import org.jfree.chart.JFreeChart; import
 * org.jfree.chart.plot.PlotOrientation; import
 * org.jfree.chart.ui.ApplicationFrame; import
 * org.jfree.data.xy.DefaultHighLowDataset;
 * 
 * 
 * public class JavaMyFrame extends ApplicationFrame {
 * 
 * 
 * 
 * public JavaMyFrame(final String title) {
 * 
 * super(title);
 * 
 * final DefaultHighLowDataset dataset = createOHLCDataset();
 * 
 * final JFreeChart chart = createChart(dataset);
 * chart.getXYPlot().zoom(HEIGHT);
 * chart.getXYPlot().setOrientation(PlotOrientation.VERTICAL);
 * 
 * final ChartPanel chartPanel = new ChartPanel(chart);
 * chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
 * 
 * setContentPane(chartPanel);
 * 
 * }
 * 
 * 
 * private JFreeChart createChart(final DefaultHighLowDataset dataset) { final
 * JFreeChart chart = ChartFactory.createCandlestickChart( "Candlestick Demo",
 * "Time", "Value", dataset, true ); return chart; }
 * 
 * 
 * 
 * 
 * 
 * private static DefaultHighLowDataset createOHLCDataset() {
 * 
 * 
 * Date[] dates = new Date[2]; double[] opens = new double[2]; double[] highs =
 * new double[2]; double[] lows = new double[2]; double[] closes = new
 * double[2]; double[] volumes = new double[2]; dates[0] = new Date(1619136000);
 * dates[1] = new Date(1620239600); for (int i = 0; i < 2; i++) {
 * 
 * 
 * opens[i] =36; highs[i] = 39; lows[i] = 29; closes[i] = 50; volumes[i] = 0; }
 * 
 * return new DefaultHighLowDataset("deneme", dates, highs, lows, opens, closes,
 * volumes); }
 * 
 * }
 */

