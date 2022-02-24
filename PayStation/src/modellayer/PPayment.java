package modellayer;

import controllayer.IllegalCoinException;
import utility.Calculation;
import utility.Validation;

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
	
	public int getTimeBoughtInMinutes() {
		PPrice aPrice = new PPrice();
		int timeBoughtInMinutes = 0;

		double timeBoughtInSeconds = this.amount * aPrice.getParkingPrice();
		timeBoughtInMinutes = (int) ((timeBoughtInSeconds + 59) / 60);

		return timeBoughtInMinutes;
	}
	
	public void validateCoin(Coin coin) throws IllegalCoinException {
		
		Validation.validateCoin(coin);	
	}	
}
