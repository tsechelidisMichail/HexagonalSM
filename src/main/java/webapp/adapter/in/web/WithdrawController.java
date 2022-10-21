package webapp.adapter.in.web;

import webapp.application.port.in.DepositCommandData;
import webapp.application.port.in.WithdrawUseCase;
import webapp.application.service.WithdrawService;

class WithdrawController {
	private final WithdrawUseCase depositUseCase = new WithdrawService();

	String withdraw(String[] data) {
		DepositCommandData command = new DepositCommandData(data);
		return depositUseCase.withdraw(command);
	}

}
