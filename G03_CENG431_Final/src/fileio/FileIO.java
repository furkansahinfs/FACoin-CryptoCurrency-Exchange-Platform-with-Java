package fileio;

import fileio.parser.Parser;
import model.User;
import model.Wallet;
import storage.IContainer;
import storage.UserContainer;
import storage.WalletContainer;

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
	public IContainer<Wallet> readCryptoWallets(String filePath) throws Exception {
		IContainer<Wallet> cryptoWallets = null;

		String fileAll = fRead.readFile(filePath);// read file
		if (!fileAll.isBlank())// if not blank
			cryptoWallets = parser.parseCryptoWallets(fileAll); // parse wallets
		else
			cryptoWallets = new WalletContainer(); // initialise empty container

		return cryptoWallets;
	}
	
	@Override
	public IContainer<Wallet> readBankWallets(String filePath) throws Exception {
		IContainer<Wallet> bankWallets = null;

		String fileAll = fRead.readFile(filePath);// read file
		if (!fileAll.isBlank())// if not blank
			bankWallets = parser.parseCryptoWallets(fileAll); // parse wallets
		else
			bankWallets = new WalletContainer(); // initialise empty container

		return bankWallets;
	}

	@Override
	public IContainer<User> readUsers(String filePath) throws Exception {
		IContainer<User> users = null;

		String fileAll = fRead.readFile(filePath); // read file
		if (!fileAll.isBlank()) // if not blank
			users = parser.parseUsers(fileAll); // parse users
		else
			users = new UserContainer(); // initialise empty container

		return users;
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


}
