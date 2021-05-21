package mediator;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import fileio.repository.UserRepository;
import fileio.repository.DatabaseResult;
import storage.IContainer;
import view.LabelInfo;
import view.color.*;
import model.Transaction;
import model.User;

public class OrderListMediator {

	private final String userId;
	
	public OrderListMediator(String userId) {
		this.userId = userId;
	}

	private List<LabelInfo> getLabel(IContainer<Transaction> transactions) {

		List<LabelInfo> labels = new ArrayList<LabelInfo>();
		for (Transaction transaction : transactions) {
			ColorPalette palette = new ColorPalette(new DarkTheme());
			Color color = palette.SECOND_COLOR;
			if(transaction.getTransactionState().equals("Pending"))
			{
				color = palette.FIRST_COLOR;
			}
			LabelInfo newLabel = new LabelInfo(color, transaction.getCoinQuantity(), (transaction.getId() + " : " + transaction.getCoin().getName()), transaction.getCoinValue(), transaction.getBanknote().getName());
			labels.add(newLabel);
		}
		return labels;
	}

	public DefaultListModel<JLabel> getList() {
		Thread.LOCK_MUTEX(Thread.MUTEX);
		DefaultListModel<JLabel> list = new DefaultListModel<JLabel>();
		DatabaseResult userResult = (new UserRepository()).getById(userId);
		if(userResult.getObject() == null)
		{
			return list;
		}
		IContainer<Transaction> userTransactions = ((User) userResult.getObject()).getTransactions();
		setLabels(getLabel(userTransactions),list);
		Thread.UNLOCK_MUTEX(Thread.MUTEX);
		return list;
	}

	public void setLabels(List<LabelInfo> labels, DefaultListModel<JLabel> listModel) {
		int index = 0;
		for (LabelInfo labelInfo : labels) {
			JLabel label = createJLabel(labelInfo);
			listModel.add(index, label);
			index++;
		}
	}

	public JLabel createJLabel(LabelInfo label) {

		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(4);
		ColorPalette palette = new ColorPalette(new DarkTheme());
		String transactionType = "Approved";
		if(label.color == palette.FIRST_COLOR)
		{
			transactionType = "Pending";
		}
		String text = label.coinName + "/" + label.banknote + "/" + transactionType + " :\tQuantity = " + label.value + " - CoinValue = " + df.format(label.percent);

		JLabel jLabel = new JLabel(text);
		jLabel.setForeground(label.color);

		return jLabel;
	}
}
