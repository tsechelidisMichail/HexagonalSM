package webApp.adapter.out.persistense;

import webApp.domain.Account;

class GetAccountQuery {

	public Account getAccount() {
		//TODO: SQL MAGIC, initialize with db money
		return new Account(/*get money from db*/0);
	}

}
