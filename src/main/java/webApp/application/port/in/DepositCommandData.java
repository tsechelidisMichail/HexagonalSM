package webApp.application.port.in;

public class DepositCommandData {
	private int depositMoney = 0;
	
	public DepositCommandData(String[] data) {
		try{
			depositMoney = Integer.parseInt(data[1]);
		}catch(ArrayIndexOutOfBoundsException |NumberFormatException e) {
			e.printStackTrace();
		}
		//Here we translate data
		
	}
	
	public int getDepositMoney() {
		//MUST NOT BE NULL
		return depositMoney;
	}
	

}
