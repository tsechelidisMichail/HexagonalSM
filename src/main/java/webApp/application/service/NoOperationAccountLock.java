package account.application.service;

import account.application.port.out.AccountLock;
import account.domain.Account;

class NoOperationAccountLock implements AccountLock {

	@Override
	public void lockAccount(Account accountId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void releaseAccount(Account accountId) {
		// TODO Auto-generated method stub

	}

}
