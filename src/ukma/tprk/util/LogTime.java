package ukma.tprk.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogTime {
	public static String getCurrentTimeStamp() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return "[" + strDate + "] ";
	}
}
