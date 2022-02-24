package modellayer;

import utility.Calculation;

public class PPayment {

	private double amount = 0;
	
	public PPayment() {
		
	}
	
	public PPayment(double amount) {
		this.amount = amount;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public void addAmount(Coin coin, PPrice currentPrice) {
	
		this.amount += Calculation.getCoinValueInCent(coin, currentPrice);
		
	}

}
