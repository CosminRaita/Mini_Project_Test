package test;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllayer.IllegalCoinException;
import modellayer.Coin;
import modellayer.Currency;
import modellayer.PLot;
import modellayer.PPayStation;
import modellayer.PZone;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik Bærbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestIllegalCoin {

	PPayStation ps;
	
	/** Fixture for pay station testing. */
	@BeforeEach
	public void setUp() {
		ps = new PPayStation(1, "P-423E", new PLot(2, "Ved vandmaden", 9200, new PZone(2, "B Zone")));
	}

	/**
	 * Verify that illegal coins are rejected.
	 */
	
	@Test
	public void negativeAmountIsInvalidCoin() {
		Coin coin = new Coin(-1, Currency.ValidCurrency.DKK, Currency.ValidCoinType.INTEGER);
		
		Assertions.assertThrows(IllegalCoinException.class, () -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void nonIntegerAmountIsInvalidCoin() {
		Coin coin = new Coin(1/2, Currency.ValidCurrency.DKK, Currency.ValidCoinType.INTEGER);
		
		Assertions.assertThrows(IllegalCoinException.class, () -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void nonIntegerNegativeAmountIsInvalidCoin() {
		Coin coin = new Coin(-1/2, Currency.ValidCurrency.DKK, Currency.ValidCoinType.INTEGER);
		
		Assertions.assertThrows(IllegalCoinException.class, () -> {
			ps.validateCoin(coin);
		});
	}
	
	
	@Test
	public void poundsIsInvalidCurrency() {
		Coin coin = new Coin(1, Currency.ValidCurrency.GBP, Currency.ValidCoinType.INTEGER);
		
		Assertions.assertThrows(IllegalCoinException.class, () -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void swedishCrownIsInvalidCurrency() {
		Coin coin = new Coin(1, Currency.ValidCurrency.SEK, Currency.ValidCoinType.INTEGER);
		
		Assertions.assertThrows(IllegalCoinException.class, () -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void norwegianCrownIsInvalidCurrency() {
		Coin coin = new Coin(1, Currency.ValidCurrency.NOK, Currency.ValidCoinType.INTEGER);
		
		Assertions.assertThrows(IllegalCoinException.class, () -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void zeroEurosIsInvalidCoin() {
		Coin coin = new Coin(0, Currency.ValidCurrency.EURO, Currency.ValidCoinType.INTEGER);
		
		Assertions.assertThrows(IllegalCoinException.class, () -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void oneEuroIsValidCoin() {
		Coin coin = new Coin(1, Currency.ValidCurrency.EURO, Currency.ValidCoinType.INTEGER);
		
		Assertions.assertDoesNotThrow(() -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void twoEurosIsValidCoin() {
		Coin coin = new Coin(2, Currency.ValidCurrency.EURO, Currency.ValidCoinType.INTEGER);
		
		Assertions.assertDoesNotThrow(() -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void threeEurosIsInvalidCoin() {
		Coin coin = new Coin(3, Currency.ValidCurrency.EURO, Currency.ValidCoinType.INTEGER);
		
		Assertions.assertThrows(IllegalCoinException.class, () -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void zeroCentsIsInvalidCoin() {
		Coin coin = new Coin(0, Currency.ValidCurrency.EURO, Currency.ValidCoinType.FRACTION);
		
		Assertions.assertThrows(IllegalCoinException.class, () -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void oneCentIsValidCoin() {
		Coin coin = new Coin(1, Currency.ValidCurrency.EURO, Currency.ValidCoinType.FRACTION);
		
		Assertions.assertDoesNotThrow(() -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void twoCentsIsValidCoin() {
		Coin coin = new Coin(2, Currency.ValidCurrency.EURO, Currency.ValidCoinType.FRACTION);
		
		Assertions.assertDoesNotThrow(() -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void threeCentsIsInvalidCoin() {
		Coin coin = new Coin(3, Currency.ValidCurrency.EURO, Currency.ValidCoinType.FRACTION);
		
		Assertions.assertThrows(IllegalCoinException.class, () -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void fourCentsIsInvalidCoin() {
		Coin coin = new Coin(4, Currency.ValidCurrency.EURO, Currency.ValidCoinType.FRACTION);
		
		Assertions.assertThrows(IllegalCoinException.class, () -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void fiveCentsIsValidCoin() {
		Coin coin = new Coin(5, Currency.ValidCurrency.EURO, Currency.ValidCoinType.FRACTION);
		
		Assertions.assertDoesNotThrow(() -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void sixCentsIsInvalidCoin() {
		Coin coin = new Coin(6, Currency.ValidCurrency.EURO, Currency.ValidCoinType.FRACTION);
		
		Assertions.assertThrows(IllegalCoinException.class, () -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void nineCentsIsInvalidCoin() {
		Coin coin = new Coin(9, Currency.ValidCurrency.EURO, Currency.ValidCoinType.FRACTION);
		
		Assertions.assertThrows(IllegalCoinException.class, () -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void tenCentsIsValidCoin() {
		Coin coin = new Coin(10, Currency.ValidCurrency.EURO, Currency.ValidCoinType.FRACTION);
		
		Assertions.assertDoesNotThrow(() -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void elevenCentsIsInvalidCoin() {
		Coin coin = new Coin(11, Currency.ValidCurrency.EURO, Currency.ValidCoinType.FRACTION);
		
		Assertions.assertThrows(IllegalCoinException.class, () -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void nineteenCentsIsInvalidCoin() {
		Coin coin = new Coin(19, Currency.ValidCurrency.EURO, Currency.ValidCoinType.FRACTION);
		
		Assertions.assertThrows(IllegalCoinException.class, () -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void twentyCentsIsValidCoin() {
		Coin coin = new Coin(20, Currency.ValidCurrency.EURO, Currency.ValidCoinType.FRACTION);
		
		Assertions.assertDoesNotThrow(() -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void twentyOneCentsIsInvalidCoin() {
		Coin coin = new Coin(21, Currency.ValidCurrency.EURO, Currency.ValidCoinType.FRACTION);
		
		Assertions.assertThrows(IllegalCoinException.class, () -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void fourtyNineCentsIsInvalidCoin() {
		Coin coin = new Coin(49, Currency.ValidCurrency.EURO, Currency.ValidCoinType.FRACTION);
		
		Assertions.assertThrows(IllegalCoinException.class, () -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void fiftyCentsIsValidCoin() {
		Coin coin = new Coin(50, Currency.ValidCurrency.EURO, Currency.ValidCoinType.FRACTION);
		
		Assertions.assertDoesNotThrow(() -> {
			ps.validateCoin(coin);
		});
	}
	
	public void fiftyOneCentsIsInvalidCoin() {
		Coin coin = new Coin(51, Currency.ValidCurrency.EURO, Currency.ValidCoinType.FRACTION);
		
		Assertions.assertThrows(IllegalCoinException.class, () -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void zeroDanishCrownsIsInvalidCoin() {
		Coin coin = new Coin(0, Currency.ValidCurrency.DKK, Currency.ValidCoinType.INTEGER);
		
		Assertions.assertThrows(IllegalCoinException.class, () -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void oneDanishCrownIsValidCoin() {
		Coin coin = new Coin(1, Currency.ValidCurrency.DKK, Currency.ValidCoinType.INTEGER);
		
		Assertions.assertDoesNotThrow(() -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void twoDanishCrownsIsValidCoin() {
		Coin coin = new Coin(2, Currency.ValidCurrency.DKK, Currency.ValidCoinType.INTEGER);
		
		Assertions.assertDoesNotThrow(() -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void threeDanishCrownsIsInvalidCoin() {
		Coin coin = new Coin(3, Currency.ValidCurrency.DKK, Currency.ValidCoinType.INTEGER);
		
		Assertions.assertThrows(IllegalCoinException.class, () -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void foursDanishCrownsIsInvalidCoin() {
		Coin coin = new Coin(4, Currency.ValidCurrency.DKK, Currency.ValidCoinType.INTEGER);
		
		Assertions.assertThrows(IllegalCoinException.class, () -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void fiveDanishCrownsIsValidCoin() {
		Coin coin = new Coin(5, Currency.ValidCurrency.DKK, Currency.ValidCoinType.INTEGER);
		
		Assertions.assertDoesNotThrow(() -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void sixDanishCrownsIsInvalidCoin() {
		Coin coin = new Coin(6, Currency.ValidCurrency.DKK, Currency.ValidCoinType.INTEGER);
		
		Assertions.assertThrows(IllegalCoinException.class, () -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void nineDanishCrownsIsInvalidCoin() {
		Coin coin = new Coin(9, Currency.ValidCurrency.DKK, Currency.ValidCoinType.INTEGER);
		
		Assertions.assertThrows(IllegalCoinException.class, () -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void tenDanishCrownsIsValidCoin() {
		Coin coin = new Coin(10, Currency.ValidCurrency.DKK, Currency.ValidCoinType.INTEGER);
		
		Assertions.assertDoesNotThrow(() -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void elevenDanishCrownsIsInvalidCoin() {
		Coin coin = new Coin(11, Currency.ValidCurrency.DKK, Currency.ValidCoinType.INTEGER);
		
		Assertions.assertThrows(IllegalCoinException.class, () -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void nineteenDanishCrownsIsInvalidCoin() {
		Coin coin = new Coin(19, Currency.ValidCurrency.DKK, Currency.ValidCoinType.INTEGER);
		
		Assertions.assertThrows(IllegalCoinException.class, () -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void twentyDanishCrownsIsValidCoin() {
		Coin coin = new Coin(20, Currency.ValidCurrency.DKK, Currency.ValidCoinType.INTEGER);
		
		Assertions.assertDoesNotThrow(() -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void twentyOneDanishCrownsIsInvalidCoin() {
		Coin coin = new Coin(21, Currency.ValidCurrency.DKK, Currency.ValidCoinType.INTEGER);
		
		Assertions.assertThrows(IllegalCoinException.class, () -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void fourtyNineDanishOreIsInvalidCoin() {
		Coin coin = new Coin(49, Currency.ValidCurrency.DKK, Currency.ValidCoinType.FRACTION);
		
		Assertions.assertThrows(IllegalCoinException.class, () -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void fiftyDanishOreIsValidCoin() {
		Coin coin = new Coin(50, Currency.ValidCurrency.DKK, Currency.ValidCoinType.FRACTION);
		
		Assertions.assertDoesNotThrow(() -> {
			ps.validateCoin(coin);
		});
	}
	
	@Test
	public void fiftyOneDanishOreIsInvalidCoin() {
		Coin coin = new Coin(51, Currency.ValidCurrency.DKK, Currency.ValidCoinType.FRACTION);
		
		Assertions.assertThrows(IllegalCoinException.class, () -> {
			ps.validateCoin(coin);
		});
	}
}
