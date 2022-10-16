package webApp.application.port.out;

import webApp.domain.Account;

public interface AccountLock {
	void lockAccount(Account accountId);

	void releaseAccount(Account accountId);
}
