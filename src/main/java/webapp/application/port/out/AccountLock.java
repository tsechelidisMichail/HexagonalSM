package webapp.application.port.out;

import webapp.domain.Account;

public interface AccountLock {
	void lockAccount(Account accountId);

	void releaseAccount(Account accountId);
}
