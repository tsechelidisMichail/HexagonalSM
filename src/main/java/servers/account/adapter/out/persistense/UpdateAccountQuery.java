package servers.account.adapter.out.persistense;

import servers.account.domain.Account;

class UpdateAccountQuery {

	public void updateAccount(Account account) {
		account.getDeposit();
		//TODO: SQL MAGIC, initialize with db money
	}
}
