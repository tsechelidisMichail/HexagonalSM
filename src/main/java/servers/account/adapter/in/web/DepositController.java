package servers.account.adapter.in.web;

import servers.account.application.port.in.DepositCommandData;
import servers.account.application.port.in.DepositUseCase;
import servers.account.application.service.DepositService;

class DepositController {
	private final DepositUseCase depositUseCase = new DepositService();

	String deposit(String[] data) {
		DepositCommandData command = new DepositCommandData(data);
		return depositUseCase.deposit(command);
	}
}
