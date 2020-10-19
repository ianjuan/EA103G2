package com.rec.model;

import java.util.Calendar;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		Integer rec_mon = cal.get(Calendar.MONTH) +1;
		System.out.println(rec_mon);
	}

}
