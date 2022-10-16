package account.application.service;

import account.adapter.out.persistense.AccountPersistenceAdapter;
import account.application.port.in.DepositCommandData;
import account.application.port.in.DepositUseCase;
import account.application.port.out.AccountLock;
import account.application.port.out.LoadAccount;
import account.application.port.out.UpdateAccount;
import account.domain.Account;

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
