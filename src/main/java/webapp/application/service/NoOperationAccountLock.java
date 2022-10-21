package webapp.application.service;

import webapp.application.port.out.AccountLock;
import webapp.domain.Account;

class NoOperationAccountLock implements AccountLock {

	@Override
	public void lockAccount(Account accountId) {
		//why does this exist in the demo

	}

	@Override
	public void releaseAccount(Account accountId) {
		
		//why does this exist in the demo
	}

}
