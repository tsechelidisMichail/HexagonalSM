package webapp.application.port.in;

public interface WithdrawUseCase {
	//Here we should create another class for withdrawData - this is testing at the moment
	String withdraw(DepositCommandData command);
}
