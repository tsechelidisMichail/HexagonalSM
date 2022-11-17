package webapp.application.service;

import webapp.adapter.out.persistense.AccountPersistenceAdapter;
import webapp.application.port.in.DepositCommandData;
import webapp.application.port.in.DepositUseCase;
import webapp.application.port.out.LoadAccount;
import webapp.application.port.out.UpdateAccount;
import webapp.domain.Account;

public class DepositService implements DepositUseCase{
	private final LoadAccount loadAccount = new AccountPersistenceAdapter();
	private final UpdateAccount updateAccount = new AccountPersistenceAdapter();

	@Override
	public String deposit(DepositCommandData commandData) {
		int money = commandData.getDepositMoney();
		
		Account account = loadAccount.loadAccount();

		if (account.deposit(money) && updateAccount.updateAccount(account)) {
			int balanceResult = account.getDeposit();
			return "Success! " + balanceResult;
		}
		return "failed";
	}

}
