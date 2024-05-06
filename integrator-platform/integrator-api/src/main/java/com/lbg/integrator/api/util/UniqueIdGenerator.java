package com.lbg.integrator.api.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
public class UniqueIdGenerator {
    private static final Calendar cal = new GregorianCalendar();

    public static final Random generator = new Random();

    public static synchronized String getUniqueIngestionId(Date date) {
        cal.setTime(date);
        int YEAR = cal.get(1);
        int DAY_OF_YEAR = cal.get(6);
        int HOUR_OF_DAY = cal.get(11);
        int Minute = cal.get(12);
        int second = cal.get(13);
        int random = 100000 + generator.nextInt(900000);
        StringBuilder str = new StringBuilder(YEAR);
        StringBuilder append = str.append(YEAR).append(DAY_OF_YEAR).append(HOUR_OF_DAY).append(Minute).append(second).append(random);
        return append.toString();
    }

    public static synchronized int getUniqueTaskId() {
        int startVal = 10000;
        int nextVal = 90000;
        return startVal + generator.nextInt(nextVal);
    }
}

