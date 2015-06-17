package de.exxeta;

import static org.junit.Assert.*;


import org.joda.time.LocalDate;
import org.junit.Test;
public class AddDateTest {

	@Test
	public void test() {
		LocalDate localDatetoAdd = new LocalDate(2015, 10, 19);
		bacaqu.Main.add(localDatetoAdd.toDate());
		boolean found = util.MongoDBHandler.findDate(localDatetoAdd);	
		assertEquals("Methode add", found, true);
	}

}
