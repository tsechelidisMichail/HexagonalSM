package account.adapter.out.persistense;

import account.application.port.out.LoadAccount;
import account.application.port.out.UpdateAccount;
import account.domain.Account;

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
