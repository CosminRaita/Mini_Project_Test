package modellayer;

import controllayer.ControlPrice;
import controllayer.IllegalCoinException;
import utility.Calculation;
import utility.Validation;

/**
 * Inspired by the book: Flexible, Reliable Software
 * Henrik Bærbak Christensen: Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class PPayStation {
	
	// PayStation ident
	private int id;	
	// PayStaion model
	private String payStationModel;
	private PPayment currentPayment;
	private ControlPrice controlPrice;
	
	
	public PPayStation(int id, String payStationModel) {
		this.id = id;
		this.payStationModel = payStationModel;
		this.currentPayment = new PPayment();
		this.controlPrice = new ControlPrice();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setAmount(double amount) {
		currentPayment.setAmount(amount);
	}
	
	public double getAmount() {
		return currentPayment.getAmount();
	}

	public String getPayStationModel() {
		return payStationModel;
	}

	public void setPayStationModel(String payStationModel) {
		this.payStationModel = payStationModel;
	}	
	
	public int getTimeBoughtInMinutes() {
		
		PPrice aPrice = controlPrice.getCurrentPrice();
		int timeBoughtInMinutes = 0;
		double amount = currentPayment.getAmount();
		
		double timeBoughtInSeconds = amount * aPrice.getParkingPrice();
		timeBoughtInMinutes = (int) ((timeBoughtInSeconds + 59) / 60);

		return timeBoughtInMinutes;
	}
	
	public void validateCoin(Coin coin) throws IllegalCoinException {
		
		Validation.validateCoin(coin);	
	}
	
	public void addAmount(Coin coin, PPrice currentPrice) {
		currentPayment.addAmount(coin, currentPrice);
	}
}
