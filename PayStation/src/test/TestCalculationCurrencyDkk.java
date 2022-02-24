package test;


import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllayer.ControlPayStation;
import controllayer.IllegalCoinException;
import modellayer.Currency;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik Bærbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestCalculationCurrencyDkk {

	ControlPayStation ps;

	/** Fixture for pay station testing. */
	@BeforeEach
	public void setUp() {
		ps = new ControlPayStation();
	}

	/**
	 * Entering 50 øre should make the display report 3 minutes parking time.
	 */
	@Test
	public void shouldDisplay3MinFor50Ore() throws IllegalCoinException {
		
		// Arrange
		int expectedParkingTime = 3;	// In minutes
		int coinValue = 50;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.DKK;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.FRACTION;
		
		// Act
		ps.addPayment(coinValue, coinCurrency, coinType);
			
		// Assert
		assertEquals(expectedParkingTime, ps.readDisplay());
	}
	
	/**
	 * Entering 1 dkk should make the display report 6 minutes parking time.
	 */
	@Test
	public void shouldDisplay6MinFor1dkk() throws IllegalCoinException {
		
		// Arrange
		int expectedParkingTime = 6;	// In minutes
		int coinValue = 1;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.DKK;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;
		
		// Act
		ps.addPayment(coinValue, coinCurrency, coinType);
			
		// Assert
		assertEquals(expectedParkingTime, ps.readDisplay());
	}
	
	/**
	 * Entering 1 dkk and 2 dkk should make the display report 16 minutes parking time.
	 */
	@Test
	public void shouldDisplay16MinFor1dkkAnd2dkk() throws IllegalCoinException {
		
		// Arrange
		int expectedParkingTime = 16;	// In minutes
		int coinValue = 1;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.DKK;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;
		
		// Act
		ps.addPayment(coinValue, coinCurrency, coinType);
		
		coinValue = 2;
		
		ps.addPayment(coinValue, coinCurrency, coinType);
			
		// Assert
		assertEquals(expectedParkingTime, ps.readDisplay());
	}


	/** Fixture for pay station testing. */
	@AfterEach
	public void cleanUp() {
		ps.setReady();
	}	
	
}
