package webApp.application.service;

import webApp.adapter.out.persistense.AccountPersistenceAdapter;
import webApp.application.port.in.DepositCommandData;
import webApp.application.port.in.DepositUseCase;
import webApp.application.port.out.AccountLock;
import webApp.application.port.out.LoadAccount;
import webApp.application.port.out.UpdateAccount;
import webApp.domain.Account;

public class DepositService implements DepositUseCase{
	private final LoadAccount loadAccount = new AccountPersistenceAdapter();
	private final AccountLock accountLock = new NoOperationAccountLock();
	private final UpdateAccount updateAccount = new AccountPersistenceAdapter();

	@Override
	public String deposit(DepositCommandData commandData) {
		int money = commandData.getDepositMoney();
		
		Account account = loadAccount.loadAccount();
		
		accountLock.lockAccount(account);
		if (!account.deposit(money)) {
			accountLock.releaseAccount(account);
			return "failed";
		}
		
		updateAccount.updateAccount(account);
		accountLock.releaseAccount(account);
		return "success" + money;
	}

}
