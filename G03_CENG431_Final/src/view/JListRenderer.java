package view;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import view.color.ColorPalette;
import view.color.DarkTheme;

public class JListRenderer extends JLabel implements ListCellRenderer<JLabel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3121097196832501690L;
	private ColorPalette palette = new ColorPalette(new DarkTheme());

	@Override
	public Component getListCellRendererComponent(JList<? extends JLabel> list, JLabel label, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		setText(label.getText());
		setForeground(label.getForeground());
		if (isSelected) {			
            setBackground(Color.BLACK);
			setOpaque(true);
         }
		else {
			setOpaque(false);
		}
		return this;
	}

}