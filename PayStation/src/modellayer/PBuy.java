package modellayer;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik Bærbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class PBuy {

	// Buy ident
	private long id;
	// Time of buy
	private LocalDateTime buyTime;
	// Parkingtime in minutes
	private int duration;
	// Payed amount in cents
	private double payedAmount;
	// PayStation that generated buy 
	private PPayStation associatedPaystation;
	
	// Constructor
	public PBuy() {
		
	}
	public PBuy(int foundId) {
		id = foundId;
	}	
	public PBuy(LocalDateTime buyTime, int parkingDuration, double payedCentAmount) {
		this.buyTime = buyTime;
		this.duration = parkingDuration;
		this.payedAmount = payedCentAmount;
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(LocalDateTime buyTime) {
		this.buyTime = buyTime;
	}

	public int getParkingDuration() {
		return duration;
	}

	public void setParkingDuration(int parkingDuration) {
		this.duration = parkingDuration;
	}

	public double getPayedCentAmount() {
		return payedAmount;
	}

	public void setPayedCentAmount(double payedCentAmount) {
		this.payedAmount = payedCentAmount;
	}

	public PPayStation getAssociatedPaystation() {
		return associatedPaystation;
	}

	public void setAssociatedPaystation(PPayStation associatedPaystation) {
		this.associatedPaystation = associatedPaystation;
	}
	
}
