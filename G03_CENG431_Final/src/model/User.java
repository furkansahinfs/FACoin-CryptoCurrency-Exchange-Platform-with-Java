package model;

import java.util.Dictionary;

public class User  extends AbstractEntity{
	
	private String userId;
	private String userName;
	private String password;
	private Wallet cryptoWallet;
	private Wallet bankWallet;
	private Dictionary<String,String> favorites;
	
	public User(String id, String userName, String password, Wallet cryptoWallet, Wallet bankWallet, Dictionary<String,String> favorites)
	{
		super(id);
		this.userName=userName;
		this.password = password;
		this.cryptoWallet=cryptoWallet;
		this.bankWallet = bankWallet;
		this.favorites = favorites;
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

	public Dictionary<String,String> getFavorites()
	{
		return favorites;
	}	
	
	public boolean equals(int userId)
	{
		return (this.userId == String.valueOf(userId));		
	}
	
	public boolean equals(String userName)
	{
		return this.userName.equals(userName);		
	}
	// TODO combo box bankaya para yükleme
}
