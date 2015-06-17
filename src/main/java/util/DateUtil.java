package util;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

public class DateUtil {

	public static LocalDate convertUtilDateToLocalDate(Date date) {
		if (date == null)
			return null;
		DateTime dt = new DateTime(date);
		return dt.toLocalDate();
	}

}
