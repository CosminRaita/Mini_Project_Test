package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllayer.ControlPrice;
import databaselayer.DatabaseLayerException;
import modellayer.PPrice;

class TestCurrentPrice {
	private ControlPrice controlPrice;
	

	@BeforeEach
	void setUp() throws Exception {
		controlPrice = new ControlPrice();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetCurrentPrice() {
		PPrice pp = controlPrice.getCurrentPrice();
		assertEquals(24, pp.getParkingPrice());
	}

	@Test
	void testRemotePrice() {
		try {
			PPrice pp = controlPrice.getPriceRemote(1);
			System.out.println(pp.getParkingPrice());
			assertEquals(35, pp.getParkingPrice());
		} catch (DatabaseLayerException e) {
			e.printStackTrace();
			fail();
		}
		
	}
}
