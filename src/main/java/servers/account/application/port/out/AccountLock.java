package servers.account.application.port.out;

import servers.account.domain.Account;

public interface AccountLock {
	void lockAccount(Account accountId);

	void releaseAccount(Account accountId);
}
