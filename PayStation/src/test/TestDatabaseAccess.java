package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

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
import modellayer.PPayStation;
import modellayer.PPrice;

//import static org.junit.Assert.*;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik Bærbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestDatabaseAccess {
	
	DBConnection con = null;
	static PBuy tempPBuy;

	/** Fixture for pay station testing. */
	@Before
	public void setUp() {
		con = DBConnection.getInstance();
	}
	
	
	@Test
	public void wasConnected() {
		assertNotNull("Connected - connection cannot be null", con);
		
		DBConnection.closeConnection();
		boolean wasNullified = DBConnection.instanceIsNull();
		assertTrue("Disconnected - instance set to null", wasNullified);
		
		con = DBConnection.getInstance();
		assertNotNull("Connected - connection cannot be null", con);		
	}
	
	
	@Test
	public void wasInsertedBuy() throws DatabaseLayerException, SQLException {
		
		// Arrange
		LocalDateTime timeNow = java.time.LocalDateTime.now();
		
		
		double payedCentAmount = 100;
		
		tempPBuy = new PBuy();
		
		PPayStation pStat = new PPayStation(1, "P-423E");
		pStat.setAmount(payedCentAmount);
		tempPBuy.setAssociatedPaystation(pStat);
		tempPBuy.setBuyTime(timeNow);
		
		DatabasePBuy dbPbuy = new DatabasePBuy();
		
		// Act
		tempPBuy.setId(dbPbuy.insertParkingBuy(tempPBuy));
		
		
		
		Connection con = DBConnection.getInstance().getDBcon();

		ResultSet rs = con.createStatement().executeQuery("SELECT * FROM PBuy WHERE id=(SELECT max(id) FROM PBuy);");

		rs.next();
		
		
		PPayStation payStation = tempPBuy.getAssociatedPaystation();
		
		assertEquals(payStation.getTimeBoughtInMinutes(), Integer.parseInt(rs.getString(3)));
		assertEquals(payStation.getAmount(), Double.parseDouble(rs.getString(4)), 0);
		assertEquals(java.sql.Date.valueOf(tempPBuy.getBuyTime().toLocalDate()).toString() + " " 
		+ java.sql.Time.valueOf(tempPBuy.getBuyTime().toLocalTime()).toString() + ".0", rs.getString(2));
		
		dbPbuy.deleteParkingBuy(tempPBuy);
		
	}	
	
	
	@Test
	public void wasRetrievedPriceDatabaselayer() {
		// Arrange
		PPrice foundPrice = null;
		int pZoneId = 2;
		DatabasePPrice dbPrice = new DatabasePPrice();

		
		// Act

		// Assert
		assertEquals("Dummy", 0, 1);
		
	}
	
	
	@Test
	public void wasRetrievedPriceControllayer() {

		// Arrange

		
		// Act

		// Assert
		assertEquals("Dummy", 0, 1);
		
	}	
	
	
	/** Fixture for pay station testing. */
	@After
	public void cleanUp() {
		DBConnection.closeConnection();
	}	
	
	@AfterClass
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
		assertEquals("One row deleted", 1, numDeleted );
	}	

}
