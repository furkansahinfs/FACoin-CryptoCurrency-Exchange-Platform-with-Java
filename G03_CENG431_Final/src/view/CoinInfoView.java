 package view;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.RectangleInsets;

import view.color.ColorPalette;
import view.list.List;


public class CoinInfoView extends AppView {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5200070696436660254L;
	private ChartPanel chartPanel;
	private ColorPalette palette;
	//return new DefaultHighLowDataset("deneme", dates, highs, lows, opens, closes, volumeSs);
	public CoinInfoView() {
		chartPanel = new ChartPanel(null);
		chartPanel.setPreferredSize(new Dimension(600,300));
		add(chartPanel);
		AppWindow.FRAME.getContentPane().add(this);
	}
	
	public void setChart(JFreeChart chart)
	{
		chart.setPadding(new RectangleInsets(0,0,0,0));
		chart.setBackgroundPaint(getBackground());
		chart.clearSubtitles();
		chartPanel.setChart(chart);
		chart.getXYPlot().zoom(HEIGHT);
        chart.getXYPlot().setOrientation(PlotOrientation.VERTICAL);
        updateUI();
	}

	@Override
	public void setPalette(ColorPalette palette) {
		this.palette = palette;
		updateColors();
		
	}

	
	
	private void updateColors() {
		setBackground(palette.BACKGROUND);
		chartPanel.setBackground(palette.BACKGROUND);
		
	}

	@Override
	public void setList(List list) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List getList() {
		// TODO Auto-generated method stub
		return null;
	}

}

