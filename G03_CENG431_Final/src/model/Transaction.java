package model;

import state.TransactionType;

public class Transaction extends AbstractEntity {
	
	private Currency coin;
	private Currency banknote;
	private TransactionType type;
	private Double banknoteQuantity;
	private Double coinQuantity;

	public Transaction(Currency coin, Currency banknote, TransactionType type, Double banknoteQuantity, Double coinQuantity){
		this.coin = coin;
		this.banknote = banknote;
		this.type = type;
		this.banknoteQuantity = banknoteQuantity;
		this.coinQuantity = coinQuantity;
	}
	
	public Transaction(Currency coin, Currency banknote, Double banknoteQuantity, Double coinQuantity){
		this.coin = coin;
		this.banknote = banknote;
		this.type = new TransactionType();
		this.banknoteQuantity = banknoteQuantity;
		this.coinQuantity = coinQuantity;
	}
	
	public Double getBanknoteQuantity(){
		return this.banknoteQuantity;
	}
	
	public Double getCoinQuantity(){
		return this.coinQuantity;
	}
	
	public String getTransactionState(){
		return this.type.getState();
	}
	
	public Currency getCoin(){
		return this.coin;
	}
	
	public Currency getBanknote(){
		return this.banknote;
	}
	
	public void approveTransaction(){
		this.type.Approve();
	}
	
	public boolean equals(String id){
		return this.getId().equals(id);
	}
	
}
