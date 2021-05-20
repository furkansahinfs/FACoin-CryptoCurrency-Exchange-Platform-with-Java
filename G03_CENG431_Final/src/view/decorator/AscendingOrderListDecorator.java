package view.decorator;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;

import view.list.List;

public class AscendingOrderListDecorator extends SortListDecorator{

	public AscendingOrderListDecorator(JListDecorator decorator) {
		super(decorator);
	}

	@Override
	public void set() {
		decorator.update();
		sort(decorator.list);
		decorator.view.updateUI();
		
	}
	
	private void sort(List list) {
		//DefaultListModel<JLabel> newList = new DefaultListModel<JLabel>();
		DefaultListModel<JLabel> viewList = list.getList();
		Arrays.sort(viewList.toArray(), new AscendingComparor());

	}
	
	class AscendingComparor implements Comparator<Object>{

		@Override
		public int compare(Object o1, Object o2) {
			JLabel temp1 = (JLabel)o1;
			JLabel temp2 = (JLabel)o2;
			Float val1 = value(temp1);
			Float val2 = value(temp2);
			int result = 0;
			if(val1>val2) {
				result = 1;
			}
			return result;
		}
		
	}
	// TODO olmadý ustam
	private Float value(JLabel label) {
		String[] splitted = label.getText().split("%");
		String[] splitted1 = splitted[0].split("\\(");
		return Float.parseFloat(splitted1[1]); // TODO neden amk nedennn
	}
}
