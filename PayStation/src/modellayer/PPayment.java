package modellayer;

import controllayer.IllegalCoinException;
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
	
	public void addAmount(double amount, Currency.ValidCurrency currency,
			Currency.ValidCoinType coinType) {
		
		double valueInCent = 0;

		if (currency == Currency.ValidCurrency.DKK) {
			PPrice nowPrice = new PPrice();
			valueInCent = getDkkCoinValueInCent(amount, coinType, nowPrice);
		} else {
			valueInCent = getEuroCoinValueInCent(amount, coinType);
		}
		
		this.amount += valueInCent;
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

	private double getEuroCoinValueInCent(double coinValue, Currency.ValidCoinType coinType) {
		double valueInCent = 0;

		if (coinType == Currency.ValidCoinType.INTEGER) {
			valueInCent = coinValue * 100;
		} else {
			valueInCent = coinValue;
		}

		return valueInCent;
	}

	private double getDkkCoinValueInCent(double coinValue, Currency.ValidCoinType coinType, PPrice price) {
		double valueInCent = 0;

		if (coinType == Currency.ValidCoinType.INTEGER) {
			valueInCent = (coinValue * 100) / price.getExchangeEuroDkk();
		} else {
			valueInCent = coinValue / price.getExchangeEuroDkk();
		}

		return valueInCent;
	}	

	
}
