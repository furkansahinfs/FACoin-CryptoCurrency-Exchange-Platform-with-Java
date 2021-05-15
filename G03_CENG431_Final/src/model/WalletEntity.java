package model;
public class WalletEntity{
	private ICurrency currency;
	private float quantity;
	public WalletEntity(ICurrency currency, float quantity){
		this.currency = currency;
		this.quantity = quantity;
	}
	
	public float getTotal(String banknoteName){
		return this.currency.getValue().get(banknoteName)*quantity;
	}
	
	public String getCurrency(){
		return currency.getClass().getSimpleName();
	}
}