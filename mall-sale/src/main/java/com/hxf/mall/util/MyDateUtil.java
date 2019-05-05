package com.hxf.mall.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyDateUtil {

	public static String getMyTime(String format) {
		// 关于日期的格式化
		SimpleDateFormat sdf = new SimpleDateFormat(format);//yyyyMMdd HH:mm:ss
		String str = sdf.format(new Date());
		return str;

	}

	public static Date getMyDate(long i) {
		// 关于日期计算
		Calendar c = Calendar.getInstance();//获取当前时间

		//c.add(Calendar.DATE, -1);//对当前时间进行加减。减一天
		c.add(Calendar.DATE, (int) i);
		return c.getTime();
	}

}
