package servers.account.application.service;

import servers.account.adapter.out.persistense.AccountPersistenceAdapter;
import servers.account.application.port.in.DepositCommandData;
import servers.account.application.port.in.DepositUseCase;
import servers.account.application.port.out.AccountLock;
import servers.account.application.port.out.LoadAccount;
import servers.account.application.port.out.UpdateAccount;
import servers.account.domain.Account;

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
