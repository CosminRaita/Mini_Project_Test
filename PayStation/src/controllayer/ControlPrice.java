package controllayer;

import databaselayer.DatabaseLayerException;
import databaselayer.DatabasePPrice;
import databaselayer.IDbPPrice;
import modellayer.PPrice;

public class ControlPrice {
	
	private IDbPPrice dbPrice;
	
	public ControlPrice() {
		this.dbPrice = new DatabasePPrice();
	}
	
	public PPrice getCurrentPrice(int zoneId) throws DatabaseLayerException {
		
		// Get price from Parkingsystem DB
		PPrice readPrice = dbPrice.getPriceByZoneId(zoneId);
		//
		return readPrice;
	}

}
