package webapp.adapter.out.persistense;

import webapp.application.port.out.LoadAccount;
import webapp.application.port.out.UpdateAccount;
import webapp.domain.Account;

public class AccountPersistenceAdapter implements LoadAccount, UpdateAccount{
	private LoadAccountQuery getAccountQuery = new LoadAccountQuery();
	private UpdateAccountQuery updateAccountQuery = new UpdateAccountQuery();
	
	@Override
	public Account loadAccount() {
		return getAccountQuery.getAccount();
	}

	@Override
	public boolean updateAccount(Account account) {
		return updateAccountQuery.updateAccount(account);	
	}
	
}
