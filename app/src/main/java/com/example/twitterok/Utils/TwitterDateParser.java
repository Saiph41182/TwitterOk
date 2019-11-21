package com.example.twitterok.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class TwitterDateParser {
    private static final String INPUT_DATE_PATTERN = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
    private static final String CREATED_DATE_PUTTERN = "EEE dd MMM yyyy HH:mm:ss";
    private static final String OLD_POST_DATE_PATTERN = "dd MMM yyyy";

    private SimpleDateFormat dataFormat;
    private Calendar currentDate;
    private Calendar calendar;
    private Date date;

    public TwitterDateParser() {
        dataFormat = new SimpleDateFormat(INPUT_DATE_PATTERN, Locale.ENGLISH);

        currentDate = Calendar.getInstance();
        calendar = new GregorianCalendar();

        dataFormat.setLenient(true);
    }

    public void setDateJson(String date) {
        try {
            this.date = dataFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String wasPosted() {
        calendar.setTime(date);
        int currentDayOfMonth = currentDate.get(Calendar.DAY_OF_MONTH);
        int postedDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int currentHours = currentDate.get(Calendar.HOUR_OF_DAY);
        int postedHours = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = currentDate.get(Calendar.MINUTE);
        int postedMinute = calendar.get(Calendar.MINUTE);
        int currentSecond = currentDate.get(Calendar.SECOND);
        int postedSeconds = calendar.get(Calendar.SECOND);

        StringBuilder sb = new StringBuilder();
        if (date != null) {
            if (calendar.get(Calendar.YEAR) != currentDate.get(Calendar.YEAR) ||
                calendar.get(Calendar.MONTH)!= currentDate.get(Calendar.MONTH)) {
                SimpleDateFormat sdf = new SimpleDateFormat(OLD_POST_DATE_PATTERN,Locale.ENGLISH);
                return sdf.format(calendar.getTime());
            }
            if (postedDayOfMonth < currentDayOfMonth) {
                return sb.append(currentDayOfMonth - postedDayOfMonth).append(" day").toString();
            }
            if (postedHours < currentHours) {
                return sb.append(currentHours - postedHours).append(" h").toString();
            }
            if (postedMinute < currentMinute) {
                return sb.append(currentMinute - postedMinute).append(" m").toString();
            }
            return sb.append(currentSecond - postedSeconds).append(" sec").toString();
        }
        return null;
    }

    public String wasCreated() {
        calendar.setTime(date);

        SimpleDateFormat sdf = new SimpleDateFormat(CREATED_DATE_PUTTERN, Locale.ENGLISH);
        return sdf.format(calendar.getTime());
    }
}
