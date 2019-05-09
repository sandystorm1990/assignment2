package au.edu.utas.shiduoz.assignment2.utils;

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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(nowDate);
    }
}
