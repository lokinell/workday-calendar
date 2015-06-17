package util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.joda.time.LocalDate;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import de.jollyday.Holiday;
import de.jollyday.HolidayManager;

public class MongoDBHandler {
	
	public static DB getDBConnection(){

		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient( "localhost" );


		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB( "bacaqu" );
		
		return db;
	}
	
	public static void initDB() throws MalformedURLException{
		
		URL url = new URL("file:src/main/resources/holidays.xml");
		HolidayManager m = HolidayManager.getInstance(url);
		Set<Holiday> holidays = m.getHolidays(2015, "bw");
		
		DB db = MongoDBHandler.getDBConnection();
		DBCollection coll = db.getCollection("dates");
		
		for (Holiday h : holidays) {
		     BasicDBObject doc = new BasicDBObject("desc", h.getDescription())
	        .append("date", h.getDate().toDate());
			coll.insert(doc);
		}
		
	}
	
	public static boolean findDate(LocalDate toFind){

		DB db = MongoDBHandler.getDBConnection();
		DBCollection coll = db.getCollection("dates");
		BasicDBObject query = new BasicDBObject("date", toFind.toDate());
		boolean found = false;
		DBCursor cursor = coll.find(query);

		try {
		   while(cursor.hasNext()) {
			   found = true;
			   System.out.println(cursor.next());
		   }
		} finally {
		   cursor.close();
		}
		
		return found;
	}

}
