package servers.account.adapter.out.persistense;

import servers.account.application.port.out.LoadAccount;
import servers.account.application.port.out.UpdateAccount;
import servers.account.domain.Account;

public class AccountPersistenceAdapter implements LoadAccount, UpdateAccount{
	private GetAccountQuery getAccountQuery = new GetAccountQuery();
	private UpdateAccountQuery updateAccountQuery = new UpdateAccountQuery();
	
	@Override
	public Account loadAccount() {
		return getAccountQuery.getAccount();
	}

	@Override
	public void updateAccount(Account account) {
		updateAccountQuery.updateAccount(account);	
	}
	
}
