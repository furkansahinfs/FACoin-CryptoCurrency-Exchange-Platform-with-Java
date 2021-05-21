package fileio.parser;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import exception.FileReadException;
import factory.TransactionFactory;
import fileio.repository.IRepository;
import fileio.repository.TransactionRepository;
import model.Transaction;

public class TransactionParser {

	private TransactionFactory transactionFactory;

	/**
	 * The TransactionParser parses the gotten transactions.json file content and creates
	 * transaction objects
	 */
	protected TransactionParser() {
		transactionFactory = new TransactionFactory();
	}

	/**
	 * The function parses gotten file content and returns the wallet container
	 * which holds created contracts
	 * 
	 * @param fileAll = contract file content
	 * @param users   = user container which holds all users
	 * @param wallets = wallet container which holds all wallets of users
	 * @return Wallet Container
	 * @throws FileReadException
	 * @throws JSONException
	 */
	protected void parseTransactions(String fileAll) throws FileReadException, JSONException {
		IRepository<Transaction> transactionRepository = new TransactionRepository();
		JSONObject jsonContracts;
		jsonContracts = (new JSONParser()).parse(fileAll); // get json object of file content
		parse(jsonContracts, transactionRepository); // parse wallets


	}

	private void parse(JSONObject jsonObject, IRepository<Transaction> transactionRepository)
			throws JSONException, FileReadException {

		// iterate json object
		Iterator<?> keys = jsonObject.keys();
		Object transaction = null;
		while (keys.hasNext()) {
			Object keyTemp = keys.next();
			if (!(keyTemp instanceof String))
				throw new JSONException("TransactionParser.parse::Key is not a string");
			// get the coin values and invoke createWallet()
			// to get created wallet
			String transactionId = (String) keyTemp;
			transaction = jsonObject.get(transactionId);
			if (transaction instanceof JSONObject) {
				String pair = ((JSONObject) transaction).getString("pair");
				String coinQuantity = ((JSONObject) transaction).getString("coinQ");
				String banknoteQuantity = ((JSONObject) transaction).getString("bankQ");
				String transactionType = ((JSONObject) transaction).getString("type");
				Transaction createdTransaction = createTransaction(pair, coinQuantity, banknoteQuantity, transactionType );
				transactionRepository.addEntity(createdTransaction);
			}
		}
	}

	private Transaction createTransaction(String pair, String coinQuantity, String banknoteQuantity, String transactionType) throws FileReadException {
		Transaction createdTransaction = transactionFactory.createTransaction(pair, coinQuantity, banknoteQuantity, transactionType);
		
		if (createdTransaction != null) {
			return createdTransaction;
		} else {
			throw new FileReadException("Wrong Format for transactions.json");
		}
	}

}
