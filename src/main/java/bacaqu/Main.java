package bacaqu;

import java.net.MalformedURLException;
import java.util.Date;

import org.joda.time.LocalDate;
import org.joda.time.Period;

import util.MongoDBHandler;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;



public class Main {
	
	public static void main(String[] args) throws MalformedURLException {

	}

	public static void add(Date toAdd) {
		
		DB db = MongoDBHandler.getDBConnection();
		DBCollection coll = db.getCollection("dates");

		BasicDBObject doc = new BasicDBObject("desc", "manueller Tag").append(
				"date", toAdd);
		coll.insert(doc);

	}

	public static void remove(Date toRemove) {

		DB db = MongoDBHandler.getDBConnection();
		DBCollection coll = db.getCollection("dates");

		BasicDBObject doc = new BasicDBObject("desc", "manueller Tag").append(
				"date", toRemove);
		coll.remove(doc);

	}

	public static Date getWorkday(Date now, int offset) {

		LocalDate returnDate = util.DateUtil.convertUtilDateToLocalDate(now)
				.minus(Period.days(offset));

		while (MongoDBHandler.findDate(returnDate) || (returnDate.dayOfWeek().getAsShortText() == "So") ) {
			returnDate = returnDate.minus(Period.days(1));
		}
		
		return returnDate.toDate();
	}
	
	public void Samples(){
		// Beispiel: Datum hinzufügen
		LocalDate localDatetoAdd = new LocalDate(2015, 10, 19);
		add(localDatetoAdd.toDate());

		// Beispiel: Datum entfernen
		LocalDate localDateToRemove = new LocalDate(2015, 10, 19);
		remove(localDateToRemove.toDate());

		// Beispiel: Datum überprüfen mit offset 1
		Date returnDate = getWorkday(new LocalDate(2015, 04, 06).toDate(), 1);
		System.out.println("Bei Eingabe " + new LocalDate(2015, 04, 06).toDate()
				+ " und offset 1 kommt das Datum: " + returnDate + " heraus");
	}

}