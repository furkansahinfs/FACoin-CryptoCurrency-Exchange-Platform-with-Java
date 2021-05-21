package fileio.writer;

import storage.IContainer;
import model.Transaction;

public class UserTransactionsWriter{
	
	protected static String writeUserTransactionsXml(IContainer<Transaction> transactions){
		String result ="<transactions>";
		for(Transaction transaction: transactions){
			result+=transaction.getId()+",";
		}
		if (result.endsWith(",")) { // if ends with , ignore it
			result = result.substring(0, result.length() - 1);
		}
		result+="</transactions>";
		return result;
	}
	
}