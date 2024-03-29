package test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllayer.ControlPrice;
//import controllayer.ControlPayStation;
//import controllayer.Currency;
//import controllayer.IPayStation;
//import controllayer.IReceipt;
//import controllayer.IllegalCoinException;
import databaselayer.DBConnection;
import databaselayer.DatabaseLayerException;
import databaselayer.DatabasePBuy;
import databaselayer.DatabasePPrice;
import modellayer.PBuy;
import modellayer.PLot;
import modellayer.PPayStation;
import modellayer.PPrice;
import modellayer.PZone;

//import static org.junit.Assert.*;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B�rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestDatabaseAccess {
	
	DBConnection con = null;
	static PBuy tempPBuy;
	private ControlPrice controlPrice;
	private DatabasePPrice databasePPrice;

	/** Fixture for pay station testing. */
	@BeforeEach
	public void setUp() {
		con = DBConnection.getInstance();
		controlPrice = new ControlPrice();
		databasePPrice = new DatabasePPrice();
	}
	
	
	@Test
	public void wasConnected() {
		assertNotNull(con, "Connected - connection cannot be null");
		
		DBConnection.closeConnection();
		boolean wasNullified = DBConnection.instanceIsNull();
		assertTrue(wasNullified, "Disconnected - instance set to null");
		
		con = DBConnection.getInstance();
		assertNotNull(con, "Connected - connection cannot be null");		
	}
	
	
	@Test
	public void wasInsertedBuy() throws DatabaseLayerException, SQLException {
		
		// Arrange
		LocalDateTime timeNow = java.time.LocalDateTime.now();
		
		double payedCentAmount = 100;
		
		PPayStation pStat = new PPayStation(1, "P-423E", new PLot(2, "Ved vandmaden", 9200, new PZone(2, "B Zone")));
		pStat.setAmount(payedCentAmount);
		
		tempPBuy = new PBuy();
		
		tempPBuy.setAssociatedPaystation(pStat);
		tempPBuy.setBuyTime(timeNow);
		
		DatabasePBuy dbPbuy = new DatabasePBuy();
		
		// Act
		tempPBuy.setId(dbPbuy.insertParkingBuy(tempPBuy));
		
		
		Connection con = DBConnection.getInstance().getDBcon();
		
		ResultSet rs = con.createStatement().executeQuery("SELECT * FROM PBuy WHERE id=(SELECT max(id) FROM PBuy);");

		rs.next();
		
		PPayStation payStation = tempPBuy.getAssociatedPaystation();
		
		int number = Integer.parseInt(rs.getString(3));
		double amount = Double.parseDouble(rs.getString(4));
		String date =  rs.getString(2);
		// Assert
		assertEquals(payStation.getTimeBoughtInMinutes(), number);
		assertEquals(payStation.getAmount(), amount, 0);
		assertEquals(java.sql.Date.valueOf(tempPBuy.getBuyTime().toLocalDate()).toString() + " " 
		+ java.sql.Time.valueOf(tempPBuy.getBuyTime().toLocalTime()).toString() + ".0", date);

		// Cleanup
		DBConnection.getInstance().getDBcon().createStatement().executeUpdate("DBCC CHECKIDENT ('PBuy', RESEED, " + (tempPBuy.getId()-1) + ")");
	}	
	
	@Test
	public void wasRetrievedPriceControlLayerZoneId0() {
		Assertions.assertThrows(DatabaseLayerException.class, () ->{
			@SuppressWarnings("unused")
			PPrice pp = controlPrice.getCurrentPrice(0);
		});	
	}

	@Test
	public void wasRetrievedPriceControlLayerZoneId1() {
		try {
			PPrice pp = controlPrice.getCurrentPrice(1);
			assertEquals(35, pp.getParkingPrice());
			assertEquals(7.5, pp.getExchangeEuroDkk());
			assertEquals(1, pp.getParkingZone().getpZoneId());
		} catch (DatabaseLayerException e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	public void wasRetrievedPriceControlLayerZoneId2() {
		try {
			PPrice pp = controlPrice.getCurrentPrice(2);
			assertEquals(25, pp.getParkingPrice());
			assertEquals(7.5, pp.getExchangeEuroDkk());
			assertEquals(2, pp.getParkingZone().getpZoneId());
		} catch (DatabaseLayerException e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	public void wasRetrievedPriceControlLayerZoneId3() {
		try {
			PPrice pp = controlPrice.getCurrentPrice(3);
			assertEquals(15, pp.getParkingPrice());
			assertEquals(7.5, pp.getExchangeEuroDkk());
			assertEquals(3, pp.getParkingZone().getpZoneId());
		} catch (DatabaseLayerException e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	public void wasRetrievedPriceControlLayerZoneId4() {
		Assertions.assertThrows(DatabaseLayerException.class, () ->{
			@SuppressWarnings("unused")
			PPrice pp = controlPrice.getCurrentPrice(4);
		});	
	}
	
	
	@Test
	public void wasRetrievedPriceDatabaseLayer() {
		PPrice pp = databasePPrice.getCurrentPrice();
		assertEquals(24, pp.getParkingPrice());
	}	
	
	@Test
	public void wasRetrievedPriceDatabaselLayerZoneId0() {
		Assertions.assertThrows(DatabaseLayerException.class, () ->{
			@SuppressWarnings("unused")
			PPrice pp = databasePPrice.getPriceByZoneId(0);
		});	
	}
	
	@Test
	public void wasRetrievedPriceDatabaseLayerZoneId1() {
		try {
			PPrice pp = databasePPrice.getPriceByZoneId(1);
			assertEquals(35, pp.getParkingPrice());
			assertEquals(7.5, pp.getExchangeEuroDkk());
			assertEquals(1, pp.getParkingZone().getpZoneId());
		} catch (DatabaseLayerException e) {
			e.printStackTrace();
			fail();
		}	
	}
	
	@Test
	public void wasRetrievedPriceDatabaseLayerZoneId2() {
		try {
			PPrice pp = databasePPrice.getPriceByZoneId(2);
			assertEquals(25, pp.getParkingPrice());
			assertEquals(7.5, pp.getExchangeEuroDkk());
			assertEquals(2, pp.getParkingZone().getpZoneId());
		} catch (DatabaseLayerException e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	public void wasRetrievedPriceDatabaseLayerZoneId3() {
		try {
			PPrice pp = databasePPrice.getPriceByZoneId(3);
			assertEquals(15, pp.getParkingPrice());
			assertEquals(7.5, pp.getExchangeEuroDkk());
			assertEquals(3, pp.getParkingZone().getpZoneId());
		} catch (DatabaseLayerException e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	public void wasRetrievedPriceDatabaselLayerZoneId4() {
		Assertions.assertThrows(DatabaseLayerException.class, () ->{
			@SuppressWarnings("unused")
			PPrice pp = databasePPrice.getPriceByZoneId(4);
		});	
	}
	
	/** Fixture for pay station testing. */
	@AfterEach
	public void cleanUp() {
		DBConnection.closeConnection();
	}	
	
	@AfterAll
	public static void cleanUpWhenFinish() {
		// 		
		// Arrange
		DatabasePBuy dbPbuy = new DatabasePBuy();
		int numDeleted = 0;
		
		// Act
		try {
			
			numDeleted = dbPbuy.deleteParkingBuy(tempPBuy);
		} catch(Exception ex) { 
			System.out.println("Error: " + ex.getMessage());
		} finally {
			DBConnection.closeConnection();
		}
	
		// Assert
		assertEquals(1, numDeleted, "One row deleted");
	}	

}
