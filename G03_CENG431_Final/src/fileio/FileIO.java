package fileio;

import fileio.parser.Parser;
import model.Currency;
import model.User;
import model.Wallet;
import storage.IContainer;
public class FileIO implements IFileIO {
	private FileRead fRead;
	private FileWrite fWrite;
	private Parser parser;

	public FileIO() {
		this.fRead = new FileRead(); // initialise file read
		this.fWrite = new FileWrite(); // initialise file write
		this.parser = new Parser();
	}

	@Override
	public void readCryptoWallets(String filePath) throws Exception {
		String fileAll = fRead.readFile(filePath);// read file
		if (!fileAll.isBlank())// if not blank
			parser.parseCryptoWallets(fileAll); // parse wallets

	}
	
	@Override
	public void readBankWallets(String filePath) throws Exception {
		String fileAll = fRead.readFile(filePath);// read file
		if (!fileAll.isBlank())// if not blank
		    parser.parseBankWallets(fileAll);// parse wallets


	}

	@Override
	public void readUsers(String filePath) throws Exception {
		String fileAll = fRead.readFile(filePath); // read file
		if (!fileAll.isBlank()) // if not blank
			parser.parseUsers(fileAll); // parse users

	}

	
	@Override
	public void writeUsers(IContainer<User> users, String filePath) throws Exception {
		fWrite.writeItems(users, filePath);
	}

	@Override
	public void writeCryptoWallets(IContainer<Wallet> cryptoWallets, String filePath) throws Exception {
		fWrite.writeItems(cryptoWallets, filePath);

	}
	
	@Override
	public void writeBankWallets(IContainer<Wallet> bankWallets, String filePath) throws Exception {
		fWrite.writeItems(bankWallets, filePath);

	}

	@Override
	public void readCoins(String filePath) throws Exception {
		String fileAll = fRead.readFile(filePath); // read file
		if (!fileAll.isBlank()) // if not blank
			parser.parseCoins(fileAll); // parse users
		
	}

	@Override
	public void readBanknotes(String filePath) throws Exception {
		String fileAll = fRead.readFile(filePath); // read file
		if (!fileAll.isBlank()) // if not blank
			parser.parseBanknotes(fileAll);// parse users
		
	}

	@Override
	public void writeCoins(IContainer<Currency> coins, String filePath) throws Exception {
		fWrite.writeItems(coins, filePath);
		
	}

	@Override
	public void writeBanknotes(IContainer<Currency> banknotes, String filePath) throws Exception {
		fWrite.writeItems(banknotes, filePath);
	}


}
