package databaselayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import modellayer.PPrice;
import modellayer.PZone;

public class DatabasePPrice implements IDbPPrice {
	
	//Hardcoded for now. TODO: Use database
	public PPrice getCurrentPrice() {
		return new PPrice();
	}
	
	public PPrice getPriceByZoneId(int zoneId) throws DatabaseLayerException {
		PPrice foundPrice = null;
		
		Calendar calendar = Calendar.getInstance();
		java.sql.Date dateNow = new java.sql.Date(calendar.getTime().getTime());
		
		Connection con = DBConnection.getInstance().getDBcon();

		String baseSelect = "select top 1 price, pZone_id from PPrice ";
		baseSelect += "where pZone_id = " + zoneId + " and starttime < '" + dateNow + "' ";
		baseSelect += "order by starttime desc";
		
		String selectZone = "select * from PZone where id =" + zoneId + ";";

		int price;
		PZone pZone; 
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			ResultSet rs = stmt.executeQuery(baseSelect);
			
			Statement stmt2 = con.createStatement();
			stmt2.setQueryTimeout(5);
			ResultSet rs2 = stmt2.executeQuery(selectZone);
			
			rs.next();
			rs2.next();
			
			price = rs.getInt(1);
			pZone = new PZone(rs2.getInt("id"), rs2.getString("name"));
			
			foundPrice = new PPrice(price,pZone,7.5);
			
			stmt.close();
		} catch (SQLException ex) {
			foundPrice = null;
			DatabaseLayerException dle = new DatabaseLayerException("Error retrieving data");
			dle.setStackTrace(ex.getStackTrace());
			throw dle;
		} catch (NullPointerException ex) {
			foundPrice = null;
			DatabaseLayerException dle = new DatabaseLayerException("Null pointer exception - possibly Connection object");
			dle.setStackTrace(ex.getStackTrace());
			throw dle;
		} catch (Exception ex) {
			foundPrice = null;
			DatabaseLayerException dle = new DatabaseLayerException("Data not retrieved! Technical error");
			dle.setStackTrace(ex.getStackTrace());
			throw dle;
		} finally {
			DBConnection.closeConnection();
		}
				
		return foundPrice;
	}
	

}
