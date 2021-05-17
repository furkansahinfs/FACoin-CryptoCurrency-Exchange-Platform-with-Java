package view.list;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;

public abstract class List extends JList<JLabel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6644497569840427309L;
	private DefaultListModel<JLabel> listModel;
	public List() {
		listModel = new DefaultListModel<JLabel>();
	}
	
	public List(DefaultListModel<JLabel> list){
		listModel = list;		
	}

	public DefaultListModel<JLabel> getList() {
		return this.listModel;
	}

	protected void setList(DefaultListModel<String> list) {
		this.getList().clear();
		updateList(list);
	}
	
	private void updateList(DefaultListModel<String> list){
		this.addAll(convertToLabel(list));
		
	}
	
	private DefaultListModel<JLabel> convertToLabel(DefaultListModel<String> list){
		DefaultListModel<JLabel> result = new DefaultListModel<JLabel>();
		int size = list.size();
		if(size<1)
			return result;
		for(int i=0;i<size;i++){
			result.addElement(new JLabel(list.get(i)));
		}
		return result;
	}
	
	private void addAll(DefaultListModel<JLabel> list){
		int size = list.size();
		if(size<1)
			return;
		for(int i=0;i<size;i++){
			this.getList().add(i, list.get(i));
			
		}	
	}

}
