package model;
public class WalletEntity{
	private ICurrency currency;
	private float quantity;
	public WalletEntity(ICurrency currency, float quantity){
		this.currency = currency;
		this.quantity = quantity;
	}
	
	public float getTotal(){
		return this.currency.getValue()*quantity;
	}
}