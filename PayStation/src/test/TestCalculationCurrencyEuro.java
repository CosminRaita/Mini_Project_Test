package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllayer.ControlPayStation;
import controllayer.IllegalCoinException;
import databaselayer.DatabaseLayerException;
import modellayer.Currency;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B�rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestCalculationCurrencyEuro {

	private ControlPayStation ps;

	/** Fixture for pay station testing. */
	@BeforeEach
	public void setUp() {
		ps = new ControlPayStation();
	}
	
	/**
	 * Entering 1 euro should make the display report 40 minutes parking time.
	 * @throws DatabaseLayerException 
	 */
	@Test
	public void shouldDisplay42MinFor1Euro() throws IllegalCoinException, DatabaseLayerException {
		
		// Arrange
		int expectedParkingTime = 42;	// In minutes
		int coinValue = 1;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;
		
		// Act
		ps.addPayment(coinValue, coinCurrency, coinType);
			
		// Assert
		System.out.println(expectedParkingTime);
		System.out.println(ps.readDisplay());
		assertEquals(expectedParkingTime, ps.readDisplay());
	}
	
	/**
	 * Entering 1 euro and 2 euro should make the display report 120 minutes parking time.
	 * @throws DatabaseLayerException 
	 */
	@Test
	public void shouldDisplay125MinFor1EuroAnd2Euro() throws IllegalCoinException, DatabaseLayerException {
		
		// Arrange
		int expectedParkingTime = 125;	// In minutes
		int coinValue = 1;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;
		
		// Act
		ps.addPayment(coinValue, coinCurrency, coinType);
		
		coinValue = 2;
		
		ps.addPayment(coinValue, coinCurrency, coinType);
			
		// Assert
		assertEquals(expectedParkingTime, ps.readDisplay());
	}
	
	/**
	 * Entering 50 cents should make the display report 20 minutes parking time.
	 * @throws DatabaseLayerException 
	 */
	@Test
	public void shouldDisplay21MinFor50cents() throws IllegalCoinException, DatabaseLayerException {
		
		// Arrange
		int expectedParkingTime = 21;	// In minutes
		int coinValue = 50;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.FRACTION;
		
		// Act
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
