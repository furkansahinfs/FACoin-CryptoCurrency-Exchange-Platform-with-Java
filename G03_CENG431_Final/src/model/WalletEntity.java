package model;
import storage.BanknoteName;
import storage.Name;
public class WalletEntity{
	private ICurrency currency;
	private Double quantity;
	public WalletEntity(ICurrency currency, Double quantity){
		this.currency = currency;
		this.quantity = quantity;
	}
	
	public double getTotal(String banknoteName){
		return this.currency.getValue().get(banknoteName)*quantity;
	}
	
	public ICurrency getCurrency() {
		return this.currency;
	}
	
	public Double getQuantity() {
		return this.quantity;
	}
	
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	
	public String getCurrencyName(){
		return currency.getClass().getSimpleName();
	}
	
	public boolean equals(Name gottenName)
	{
		return this.currency.getName().equals(gottenName.name);
	}
}