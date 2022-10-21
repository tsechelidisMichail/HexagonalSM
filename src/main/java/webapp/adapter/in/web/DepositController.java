package webapp.adapter.in.web;

import webapp.application.port.in.DepositCommandData;
import webapp.application.port.in.DepositUseCase;
import webapp.application.service.DepositService;

class DepositController {
	private final DepositUseCase depositUseCase = new DepositService();

	String deposit(String[] data) {
		DepositCommandData command = new DepositCommandData(data);
		return depositUseCase.deposit(command);
	}
}
