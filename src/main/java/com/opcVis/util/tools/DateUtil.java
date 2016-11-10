package com.opcVis.util.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private Date date;

	public DateUtil() {
		this.date = new Date();
	}

	public String nowTime(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String time = sdf.format(date);
		return time;
	}
}
