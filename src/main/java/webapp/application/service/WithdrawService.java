package webapp.application.service;

import webapp.adapter.out.persistense.AccountPersistenceAdapter;
import webapp.application.port.in.DepositCommandData;
import webapp.application.port.in.WithdrawUseCase;
import webapp.application.port.out.LoadAccount;
import webapp.application.port.out.UpdateAccount;
import webapp.domain.Account;

public class WithdrawService implements WithdrawUseCase{
	private final LoadAccount loadAccount = new AccountPersistenceAdapter();
	private final UpdateAccount updateAccount = new AccountPersistenceAdapter();

	@Override
	public synchronized String withdraw(DepositCommandData commandData) {
		int money = commandData.getDepositMoney();
		
		/*Here we test lock the entire singature/method, the query is executed when loadAccount() is invoked
		*- not when the LoadAccount is created - .
		*The lock/synchronize must be maintained until the function finishes,since we must send a request to updateAccount at db
		*
		*The lock is required in the first place since - compared to deposit - sequence in who withdraws money matters.
		*/
		Account account = loadAccount.loadAccount();
		
		if (account.withdraw(money) && updateAccount.updateAccount(account)) {
			int balanceResult = account.getDeposit();
			return "Success! " + balanceResult;
		}
		return "failed";
	}


}
