package account.application.port.out;

import account.domain.Account;

public interface AccountLock {
	void lockAccount(Account accountId);

	void releaseAccount(Account accountId);
}
