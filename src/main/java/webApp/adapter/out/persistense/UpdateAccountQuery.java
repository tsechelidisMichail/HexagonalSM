package webApp.adapter.out.persistense;

import webApp.domain.Account;

class UpdateAccountQuery {

	public void updateAccount(Account account) {
		account.getDeposit();
		//TODO: SQL MAGIC, initialize with db money
	}
}
