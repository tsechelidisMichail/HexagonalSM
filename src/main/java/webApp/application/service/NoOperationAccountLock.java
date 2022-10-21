package webApp.application.service;

import webApp.application.port.out.AccountLock;
import webApp.domain.Account;

class NoOperationAccountLock implements AccountLock {

	@Override
	public void lockAccount(Account accountId) {
		

	}

	@Override
	public void releaseAccount(Account accountId) {
		

	}

}
