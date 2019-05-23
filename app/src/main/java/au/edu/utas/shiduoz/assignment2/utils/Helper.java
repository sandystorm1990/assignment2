package au.edu.utas.shiduoz.assignment2.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {
    /**
     * get week string by the number of week
     *
     *
     * @param weekNum
     * @return
     */
    public String getWeekByWeekNum(int weekNum)
    {
        String week = "";
        if (weekNum <= 0) {
            return week;
        }
        switch (weekNum) {
            case 1:
                week = "Sunday";
                break;
            case 2:
                week = "Monday";
                break;
            case 3:
                week = "Tuesday";
                break;
            case 4:
                week = "Wednesday";
                break;
            case 5:
                week = "Thursday";
                break;
            case 6:
                week = "Friday";
                break;
            case 7:
                week = "Saturday";
                break;

            default:
                week = "";
        }
        return  week;
    }

    /**
     * padding date to a standard
     *
     * @param date
     * @return
     */
    public String paddingDate(int date)
    {
        String str = "";
        if (date<10) {
            str = "0"+date;
        }

        return  str;
    }

    /**
     * get current date time string
     *
     * @return
     */
    public String getCurrentDateTime()
    {
        Date nowDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
        return sdf.format(nowDate);
    }

    /**
     * format date
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public String formatDate(int year, int month, int day)
    {
        Date selectedDate = new Date(year-1900, month, day);
        final SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");

        return sdf.format(selectedDate);
    }

    //
    public static Date strToDate(String str)
    {
        SimpleDateFormat format = new SimpleDateFormat("EEE, YYYY-MM-DD");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  date;
    }
}
