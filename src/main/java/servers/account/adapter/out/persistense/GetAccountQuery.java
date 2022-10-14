package servers.account.adapter.out.persistense;

import servers.account.domain.Account;

class GetAccountQuery {

	public Account getAccount() {
		//TODO: SQL MAGIC, initialize with db money
		return new Account(/*get money from db*/0);
	}

}
