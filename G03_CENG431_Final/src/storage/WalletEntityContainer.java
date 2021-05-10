package storage;

import exception.NotSupportedException;
import model.WalletEntity;
public class WalletEntityContainer extends Container<WalletEntity> {

	@Override
	public WalletEntity getById(String id) throws NotSupportedException {
		throw new NotSupportedException("src.storage.WalletEntityContainer not supports getById()");
	}

	@Override
	public WalletEntity getByName(String name) throws NotSupportedException {
		throw new NotSupportedException("src.storage.WalletEntityContainer not supports getByName()");
	}

}
