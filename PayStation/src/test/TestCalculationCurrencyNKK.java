package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllayer.ControlPayStation;
import controllayer.IllegalCoinException;
import modellayer.Currency;

class TestCalculationCurrencyNKK {

	ControlPayStation ps;

	/** Fixture for pay station testing. */
	@BeforeEach
	public void setUp() {
		ps = new ControlPayStation();
	}

	/**
	 * Entering 10 nok should make raise an exception.
	 */
	@Test
	public void shouldDisplayExceptionAnd0MinFor10Nok() {
		
		// Arrange
		int expectedParkingTime = 0;	// In minutes
		
		int coinValue = 10;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.NOK;
		
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;
		
		// Assert
		Assertions.assertThrows(IllegalCoinException.class, () -> {
			// Act
			ps.addPayment(coinValue, coinCurrency, coinType);
		});
			
		assertEquals(expectedParkingTime, ps.readDisplay());
	}

	
	/** Fixture for pay station testing. */
	@AfterEach
	public void cleanUp() {
		ps.setReady();
	}
}
