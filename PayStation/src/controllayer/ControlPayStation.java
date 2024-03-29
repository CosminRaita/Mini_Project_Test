package controllayer;

import java.time.LocalDateTime;

import databaselayer.DatabaseLayerException;
import databaselayer.DatabasePBuy;
import databaselayer.IDbPBuy;
import modellayer.Coin;
import modellayer.Currency;
import modellayer.PBuy;
import modellayer.PLot;
import modellayer.PPayStation;
import modellayer.PPrice;
import modellayer.PReceipt;
import modellayer.PZone;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B�rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class ControlPayStation {

	private PPayStation payStation;
	private ControlPrice controlPrice;
	
	public ControlPayStation() {
		this.payStation = new PPayStation(1, "P-423E", new PLot(2, "Ved vandmaden", 9200, new PZone(2, "B Zone")));
		this.controlPrice = new ControlPrice();
	}
	
	

	// Receive one coin as input
	public void addPayment(int amount, Currency.ValidCurrency currency, Currency.ValidCoinType coinType) throws IllegalCoinException, DatabaseLayerException {
	
		Coin coin = new Coin(amount, currency, coinType);
		
		// Test if coin is valid
		try {	
			payStation.validateCoin(coin);
		}
		catch (IllegalCoinException coinError) {
			throw new IllegalCoinException(
					"Invalid coin: " + currency.toString() + ", " + coinType.toString() + ", " + amount);
		}
		
		PPrice currentPrice = controlPrice.getCurrentPrice(payStation.getZoneId());
		// Add amount
		payStation.addAmount(coin, currentPrice);	
	}

	// Process the buy
	public PReceipt buy() throws DatabaseLayerException {
		LocalDateTime currentTime = java.time.LocalDateTime.now();
		
		// create buy
		PBuy thisBuy = new PBuy();
		thisBuy.setAssociatedPaystation(payStation);
		thisBuy.setBuyTime(currentTime);
		
		// Save in Parkingsystem db
		IDbPBuy dbBuy = new DatabasePBuy();
		thisBuy.setId(dbBuy.insertParkingBuy(thisBuy));
		//
		ControlReceipt ctrlReceipt = new ControlReceipt(payStation.getTimeBoughtInMinutes());
		
		reset();	
		PReceipt buyReceipt = ctrlReceipt.getParkingReceipt();		
		return buyReceipt;
	}

	/**
	 * Calculate the corresponding parking time in minutes
	 * (calculated seconds and convert to minutes - rounded up)
	 * @throws DatabaseLayerException 
	*/
	public int readDisplay() throws DatabaseLayerException {
		return payStation.getTimeBoughtInMinutes();
	}	
	
	public void setReady() {
		reset();
	}

	public void cancel() {
		reset();
	}

	private void reset() {
		payStation.setAmount(0);
	}
	

	

	

	

}
