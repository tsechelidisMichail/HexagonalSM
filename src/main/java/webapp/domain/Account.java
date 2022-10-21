package webapp.domain;

public class Account {
	private int deposit;
	
	public Account(int deposit) {
		this.deposit = deposit;
	}

	public boolean deposit(int data) {
		if(data>0) {
			deposit += data;
			return true;
		}
		return false;
	}

	public int getDeposit() {
		return deposit;
	}

}
