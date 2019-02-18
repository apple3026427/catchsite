package com.catchsite.utils;

import java.util.Calendar;
import java.util.Date;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.text.SimpleDateFormat;

public class DateUtil {
	public static String convertDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return sdf.format(date);
	}
	
	/**
	 * 日期格式为 发布于昨天、发布与MM月dd日、发布与mm:ss
	 * @param grabDate
	 * @return
	 */
	public static String convertGrabDateToMyDate(String grabDate) {
		
		grabDate = grabDate.substring(3);
		Calendar calNow = Calendar.getInstance();
		Calendar calGrab = Calendar.getInstance();
		if(grabDate.equals("昨天")) {
			System.out.println(calNow.get(Calendar.YEAR) + "," + calNow.get(Calendar.MONTH) + "," + calNow.get(Calendar.DAY_OF_MONTH));
			calGrab.set(calNow.get(Calendar.YEAR), calNow.get(Calendar.MONTH), calNow.get(Calendar.DAY_OF_MONTH)-1);
		} else if(grabDate.endsWith("日")) {
			int month = Integer.valueOf(grabDate.substring(0, 2))-1;
			if(month > calNow.get(Calendar.MONTH)) {
				calGrab.set(calNow.get(Calendar.YEAR) - 1, 
						Integer.valueOf(grabDate.substring(0, 2))-1, Integer.valueOf(grabDate.substring(3, 5)));
			} else {
				calGrab.set(calNow.get(Calendar.YEAR), Integer.valueOf(grabDate.substring(0, 2))-1, Integer.valueOf(grabDate.substring(3, 5)));
			}
		} else {
			calGrab.set(calNow.get(Calendar.YEAR), calNow.get(Calendar.MONTH), calNow.get(Calendar.DAY_OF_MONTH), Integer.valueOf(grabDate.substring(0, 2)), Integer.valueOf(grabDate.substring(3)), 0);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String resultDate = sdf.format(calGrab.getTime());
		return resultDate;
	}
}
