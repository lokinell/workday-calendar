package de.exxeta;

import static org.junit.Assert.*;

import java.util.Date;

import org.joda.time.LocalDate;
import org.junit.Test;

public class GetWorkdayTest {

	@Test
	public void test() {
		Date returnDate = bacaqu.Main.getWorkday(new LocalDate(2015, 04, 06).toDate(), 1);
		assertEquals("Methode remove", returnDate, new LocalDate(2015, 04, 04).toDate());
	}

}
