package service;


public class WalletServiceParam
{
	protected String coinName;
	protected String banknoteName;
	protected Double coinQuantity;
	protected Double coinValue;
	
	public WalletServiceParam(String coinName, String banknoteName, Double coinQuantity, Double coinValue)
	{
		this.coinName = coinName;
		this.banknoteName = banknoteName;
		this.coinQuantity = coinQuantity;
		this.coinValue = coinValue;
	}
	
}