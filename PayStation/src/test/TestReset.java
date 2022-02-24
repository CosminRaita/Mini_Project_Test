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
 * Inspired by the book: Flexible, Reliable Software Henrik Bærbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestReset {

	ControlPayStation ps;

	/** Fixture for pay station testing. */
	@BeforeEach
	public void setUp() {
		ps = new ControlPayStation();
	}

	/**
	 * Verify that the pay station is cleared and display shows 0 after a buy scenario
	 */
	@Test
	public void shouldClearAfterBuy() throws IllegalCoinException, Exception {
		// Arrange
		int expectedValue = 0;
		int paying = 1;
		
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;
		
		// Act
		
		ps.addPayment(paying, coinCurrency, coinType);
		
		ps.buy();
		
		// Assert
		assertEquals(expectedValue, ps.readDisplay());
		
	}

	/**
	 * Verify that cancel() clears the pay station
	 * @throws DatabaseLayerException 
	 */
	@Test
	public void shouldClearAfterCancel() throws IllegalCoinException, DatabaseLayerException {
		// Arrange
		int expectedValue = 0;
		int paying = 1;
		
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;
		
		// Act
		
		ps.addPayment(paying, coinCurrency, coinType);
		
		ps.cancel();
		
		// Assert
		assertEquals(expectedValue, ps.readDisplay());
	}
	
	/** Fixture for pay station testing. */
	@AfterEach
	public void cleanUp() {
		ps.setReady();
	}
}
