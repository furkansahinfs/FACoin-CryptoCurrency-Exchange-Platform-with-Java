package view.decorator;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;

import view.list.CoinList;
import view.list.List;

public class AscendingOrderListDecorator extends SortListDecorator{

	public AscendingOrderListDecorator(JListDecorator decorator) {
		super(decorator);
	}

	@Override
	public void set() {
		decorator.update();
		List temp = sort(decorator.list);
		decorator.view.setList(temp);
		
	}
	
	private List sort(List list) {
		//DefaultListModel<JLabel> newList = new DefaultListModel<JLabel>();
		DefaultListModel<JLabel> viewList = list.getList();
		Object[] listArray = viewList.toArray();
		Arrays.sort(listArray, new AscendingComparor());
		List listTemp=new CoinList(convert(listArray));
		return listTemp;
	}
	
	private DefaultListModel<JLabel> convert(Object[] listA) {
		DefaultListModel<JLabel> tempL = new DefaultListModel<JLabel>();
		for(int i=0;i<listA.length;i++) {
			tempL.addElement((JLabel)listA[i]);
		}
		return tempL;
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
		return Float.parseFloat(splitted1[1].replaceAll(",",".")); // TODO neden amk nedennn
	}
}
