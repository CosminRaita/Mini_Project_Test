package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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

public class TestCalculationCurrencyMixed {

	ControlPayStation ps;

	/** Fixture for pay station testing. */
	@BeforeEach
	public void setUp() {
		ps = new ControlPayStation();
	}

	/**
	 * Entering 1 euro and 1 dkk should make the display report 46 minutes parking time.
	 * @throws DatabaseLayerException 
	 */
	@Test
	public void shouldDisplay48MinFor1DkkAnd1Euro() throws IllegalCoinException, DatabaseLayerException {
		
		// Arrange
		int expectedParkingTime = 48;	// In minutes
		
		int coinValue = 1;
		Currency.ValidCurrency coinCurrencyEuro = Currency.ValidCurrency.EURO;
		Currency.ValidCurrency coinCurrencyDkk = Currency.ValidCurrency.DKK;
		
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;
		
		// Act
		ps.addPayment(coinValue, coinCurrencyEuro, coinType);
		ps.addPayment(coinValue, coinCurrencyDkk, coinType);
			
		// Assert
		assertEquals(expectedParkingTime, ps.readDisplay());
	}
	
	/**
	 * Entering 1 euro, 1 dkk, 50 cents and 50 ore should make the display report 68 minutes parking time.
	 * @throws DatabaseLayerException 
	 */
	@Test
	public void shouldDisplay71MinFor1DkkAnd1EuroAnd50centsAnd50ore() throws IllegalCoinException, DatabaseLayerException {
		
		// Arrange
		int expectedParkingTime = 71;	// In minutes
		
		int coinValue = 1;
		Currency.ValidCurrency coinCurrencyEuro = Currency.ValidCurrency.EURO;
		Currency.ValidCurrency coinCurrencyDkk = Currency.ValidCurrency.DKK;
		
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;
		
		// Act
		ps.addPayment(coinValue, coinCurrencyEuro, coinType);
		ps.addPayment(coinValue, coinCurrencyDkk, coinType);
		
		coinValue = 50;
		
		coinType = Currency.ValidCoinType.FRACTION;
		
		ps.addPayment(coinValue, coinCurrencyEuro, coinType);
		ps.addPayment(coinValue, coinCurrencyDkk, coinType);
		
			
		// Assert
		assertEquals(expectedParkingTime, ps.readDisplay());
	}
	
	/**
	 * Entering 10 nok and 1 euro should make raise an exception and 40 min.
	 * @throws DatabaseLayerException 
	 */
	@Test
	public void shouldDisplayExceptionAnd42MinFor10NokAnd1Euro() throws IllegalCoinException, DatabaseLayerException {
		
		// Arrange
		int expectedParkingTime = 42;	// In minutes
		
		int coinValue = 1;
		Currency.ValidCurrency coinCurrencyNok = Currency.ValidCurrency.NOK;
		Currency.ValidCurrency coinCurrencyEuro = Currency.ValidCurrency.EURO;
		
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;
		
		ps.addPayment(coinValue, coinCurrencyEuro, coinType);
		
		// Assert
		Assertions.assertThrows(IllegalCoinException.class, () -> {
			// Act
			ps.addPayment(coinValue, coinCurrencyNok, coinType);
		});
			
		assertEquals(expectedParkingTime, ps.readDisplay());
	}

	
	/** Fixture for pay station testing. */
	@AfterEach
	public void cleanUp() {
		ps.setReady();
	}
	
}
