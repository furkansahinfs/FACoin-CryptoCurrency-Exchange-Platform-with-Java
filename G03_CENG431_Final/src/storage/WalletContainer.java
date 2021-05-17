package storage;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import model.Wallet;

public class WalletContainer extends Container<Wallet> {

	@Override
	public Wallet getById(String id) throws ItemNotFoundException, NotSupportedException {
		Wallet returnedWallet = null;
		for (Wallet wallet : this.getContainer()) {
			if(wallet.getId().equals(id))
			{
				returnedWallet =  wallet;
			}			
		}
		// TODO Auto-generated method stub
		return returnedWallet;
	}

	@Override
	public Wallet getByName(String name) throws ItemNotFoundException, NotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

}
