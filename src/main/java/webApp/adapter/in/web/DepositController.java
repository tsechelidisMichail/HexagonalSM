package webApp.adapter.in.web;

import webApp.application.port.in.DepositCommandData;
import webApp.application.port.in.DepositUseCase;
import webApp.application.service.DepositService;

class DepositController {
	private final DepositUseCase depositUseCase = new DepositService();

	String deposit(String[] data) {
		DepositCommandData command = new DepositCommandData(data);
		return depositUseCase.deposit(command);
	}
}
