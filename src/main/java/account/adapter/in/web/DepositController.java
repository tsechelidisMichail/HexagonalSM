package account.adapter.in.web;

import account.application.port.in.DepositCommandData;
import account.application.port.in.DepositUseCase;
import account.application.service.DepositService;

class DepositController {
	private final DepositUseCase depositUseCase = new DepositService();

	String deposit(String[] data) {
		DepositCommandData command = new DepositCommandData(data);
		return depositUseCase.deposit(command);
	}
}
