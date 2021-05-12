package model;

public class User  extends AbstractEntity{
	
	private String userId;
	private String userName;
	private String password;
	private Wallet cryptoWallet;
	private Wallet bankWallet;
	
	public User(String id, String userName, String password, Wallet cryptoWallet, Wallet bankWallet)
	{
		super(id);
		this.userName=userName;
		this.password = password;
		this.cryptoWallet=cryptoWallet;
		this.bankWallet = bankWallet;
	}
	public String getPassword() {
		return password;
	}
	public String getUserId() {
		return userId;
	}
	public String getUserName() {
		return userName;
	}
	public Wallet getCryptoWallet() {
		return cryptoWallet;
	}
	public Wallet getBankWallet() {
		return bankWallet;
	}
	
	public boolean equals(int userId)
	{
		return (this.userId == String.valueOf(userId));		
	}
	
	public boolean equals(String userName)
	{
		return (this.userName == userName);		
	}

}
