 package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import view.color.ColorPalette;
import view.list.List;


public class CoinInfoView extends AppView {

	private static final long serialVersionUID = -5200070696436660254L;
	private ChartPanel chartPanel;
	private ColorPalette palette;
	private JButton back;
	private JLabel coinValue;
	private JButton dayCandle;
	private JButton hourCandle;
	private JButton sell;
	private JButton buy;
	private JTextField buyQuantity;
	private JTextField sellQuantity;
	private JTextField buyPrice;
	private JTextField sellPrice;
	
	public CoinInfoView() {
		
		back = new JButton("Back");
		back.setFont(new Font("Arial", Font.BOLD, 14));
		back.setBounds(50, 10, 90, 30);
		add(back);
		
		coinValue = new JLabel();
		coinValue.setFont(new Font("Arial", Font.BOLD, 14));
		coinValue.setBounds(0, 600, 90, 30);
		add(coinValue);
		
		chartPanel = new ChartPanel(null);
		chartPanel.setPreferredSize(new Dimension(600,300));
		add(chartPanel);
		
		dayCandle = new JButton("DAY");
		dayCandle.setBounds(120, 650, 90, 30);
		add(dayCandle);
		
		hourCandle = new JButton("HOUR");
		hourCandle.setBounds(230, 650, 90, 30);
		add(hourCandle);
		
		sell = new JButton("SELL");
		sell.setBounds(340, 690, 90, 30);
		add(sell);
		
		buy = new JButton("BUY");
		buy.setBounds(340, 690, 90, 30);
		add(buy);
		
		buyQuantity = new JTextField("BUY");
		buyQuantity.setBounds(340, 690, 90, 30);
		add(buyQuantity);
		
		sellQuantity = new JTextField("SELL");
		sellQuantity.setBounds(340, 690, 90, 30);
		add(sellQuantity);
		
		buyPrice = new JTextField("BUYPrice");
		buyPrice.setBounds(340, 690, 90, 30);
		add(buyPrice);
		
		sellPrice = new JTextField("SellPrice");
		sellPrice.setBounds(340, 690, 90, 30);
		add(sellPrice);
		
		AppWindow.FRAME.getContentPane().add(this);
	}
	
	
	public void addBackButtonListener(ActionListener listener) {
		back.addActionListener(listener);
	}
	
	public void addDayCandleButtonListener(ActionListener listener) {
		dayCandle.addActionListener(listener);
	}
	
	public void addHourCandleButtonListener(ActionListener listener) {
		hourCandle.addActionListener(listener);
	}
	
	public void addBuyButtonListener(ActionListener listener) {
		buy.addActionListener(listener);
	}
	
	public void addSellButtonListener(ActionListener listener) {
		sell.addActionListener(listener);
	}
	
	public void setChart(JFreeChart chart)
	{
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
		coinValue.setForeground(palette.FIRST_COLOR);
		dayCandle.setBackground(palette.BACKGROUND);
		dayCandle.setForeground(palette.SECOND_COLOR);
		hourCandle.setBackground(palette.BACKGROUND);
		hourCandle.setForeground(palette.SECOND_COLOR);
		
	}
	
	@Override
	public void setLabel(JLabel coinValue) {
		this.coinValue.setText(coinValue.getText());
		updateUI();
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

