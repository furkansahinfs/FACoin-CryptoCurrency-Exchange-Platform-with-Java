package model;
import storage.Name;
public class WalletEntity{
	private ICurrency currency;
	private Double quantity;
	public WalletEntity(ICurrency currency, Double quantity){
		this.currency = currency;
		this.quantity = quantity;
	}
	
	public double getTotal(String banknoteName){
		Double value = this.currency.getValue().get(banknoteName); 
		if(value == null)
			value = 1.0;
		return value*quantity;
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