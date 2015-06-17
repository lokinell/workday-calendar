package de.exxeta;

import static org.junit.Assert.*;

import org.joda.time.LocalDate;
import org.junit.Test;

public class RemoveDateTest {

	@Test
	public void test() {
		LocalDate localDateToRemove = new LocalDate(2015, 10, 19);
		bacaqu.Main.remove(localDateToRemove.toDate());
		boolean found = util.MongoDBHandler.findDate(localDateToRemove);
		assertEquals("Methode remove", found, false);
	}

}
