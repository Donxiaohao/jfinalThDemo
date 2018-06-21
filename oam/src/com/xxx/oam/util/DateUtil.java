package com.xxx.oam.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 处理时间的工具类
 * @author th
 *
 */
public class DateUtil {
	public static String getNowDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date());
	}
}
