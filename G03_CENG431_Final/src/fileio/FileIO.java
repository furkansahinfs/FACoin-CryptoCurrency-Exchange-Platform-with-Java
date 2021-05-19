package fileio;

import exception.FileReadException;
import exception.FileWriteException;
import fileio.parser.Parser;
import fileio.writer.Writer;
import model.Currency;
import model.User;
import model.Wallet;
import storage.IContainer;
public class FileIO implements IFileIO {
	private FileRead fRead;
	private FileWrite fWrite;
	private Parser parser;
	private Writer writer;

	public FileIO() {
		this.fRead = new FileRead(); // initialise file read
		this.fWrite = new FileWrite(); // initialise file write
		this.parser = new Parser();
		this.writer = new Writer();
	}

	@Override
	public void readCryptoWallets(String filePath) throws FileReadException {
		String fileAll = fRead.readFile(filePath);// read file
		if (!fileAll.isBlank())// if not blank
			parser.parseCryptoWallets(fileAll); // parse wallets

	}
	
	@Override
	public void readBankWallets(String filePath) throws FileReadException {
		String fileAll = fRead.readFile(filePath);// read file
		if (!fileAll.isBlank())// if not blank
		    parser.parseBankWallets(fileAll);// parse wallets


	}
	// TODO view konusunda IColorable diye biþi yapabilriz
	@Override
	public void readUsers(String filePath) throws FileReadException {
		String fileAll = fRead.readFile(filePath); // read file
		if (!fileAll.isBlank()) // if not blank
			parser.parseUsers(fileAll); // parse users

	}
	@Override
	public void readCoins(String filePath) throws FileReadException {
		String fileAll = fRead.readFile(filePath); // read file
		if (!fileAll.isBlank()) // if not blank
			parser.parseCoins(fileAll); // parse users
		
	}

	@Override
	public void readBanknotes(String filePath) throws FileReadException {
		String fileAll = fRead.readFile(filePath); // read file
		if (!fileAll.isBlank()) // if not blank
			parser.parseBanknotes(fileAll);// parse users
		
	}
	
	@Override
	public void writeUsers(IContainer<User> users, String filePath) throws FileWriteException {
		String userResult = writer.writeUsersToString(users);
		fWrite.writeFileContent(userResult, filePath);
	}

	@Override
	public void writeCryptoWallets(IContainer<Wallet> cryptoWallets, String filePath) throws FileWriteException {
		String cryptoWalletResult = writer.writeCryptoWalletsToString(cryptoWallets);
		fWrite.writeFileContent(cryptoWalletResult, filePath);

	}
	
	@Override
	public void writeBankWallets(IContainer<Wallet> bankWallets, String filePath) throws FileWriteException {
		String bankWalletResult = writer.writeBankWalletsToString(bankWallets);
		fWrite.writeFileContent(bankWalletResult, filePath);

	}

	@Override
	public void writeCoins(IContainer<Currency> coins, String filePath) throws FileWriteException {
		String coinResult = writer.writeCoinsToString(coins);
		fWrite.writeFileContent(coinResult, filePath);
		
	}

	@Override
	public void writeBanknotes(IContainer<Currency> banknotes, String filePath) throws FileWriteException {
		String coinResult = writer.writeCoinsToString(banknotes);
		fWrite.writeFileContent(coinResult, filePath);
	}


}
