package au.edu.utas.shiduoz.assignment2.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    public static String formatDate(int year, int month, int day)
    {
        Date selectedDate = new Date(year-1900, month, day);
        final SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");

        return sdf.format(selectedDate);
    }

    /**
     * string date to date
     *
     * @param str
     * @return
     */
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

    /**
     * show image
     *
     * @param myImageView
     * @param path
     */
    public static void setPic(ImageView myImageView, String path)
    {
        // Get the dimensions of the View
//        int targetW = myImageView.getWidth();
//        int targetH = myImageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = 1;//Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(path, bmOptions);
        myImageView.setImageBitmap(bitmap);
    }

    /**
     * show image
     *
     * @param myImageView
     * @param path
     */
    public static void showPic(LinearLayout ll, ImageView myImageView, String path)
    {
        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;
        Log.d("photoH", photoH+"");
        Log.d("photoW", photoW+"");
        //myImageView.setMinimumHeight(photoH);
        //myImageView.setMinimumWidth(photoW);
        ViewGroup.LayoutParams llParams = ll.getLayoutParams();
        llParams.height += photoH;
        ll.setLayoutParams(llParams);
        ViewGroup.LayoutParams params = myImageView.getLayoutParams();
        params.height = photoH;
        params.width = photoW;
        myImageView.setLayoutParams(params);
        // Get the dimensions of the View
        int targetW = 1;//myImageView.getWidth();
        int targetH = 1;//myImageView.getHeight();

        // Determine how much to scale down the image
        int scaleFactor = 1;//Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(path, bmOptions);
        myImageView.setImageBitmap(bitmap);
    }

    /**
     *
     * @param path
     * @return
     */
    public static Bitmap uriConvertToBitmap(String path)
    {
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = 1;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(path, bmOptions);

        return bitmap;
    }

    /**
     * calculate date by range
     *
     * @param range
     * @return
     */
    public static String calFromDate(String range)
    {
        String fromDate = "";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");;
        try {
            switch (range) {
                case "day":
                    date = new Date();
                    fromDate = sdf.format(date);
                    break;
                case "week":
                    Calendar ca = Calendar.getInstance();
                    ca.setTime(sdf.parse(sdf.format(date)));
                    int dayWeek = ca.get(Calendar.DAY_OF_WEEK)-1;
                    if (0 == dayWeek) {
                        dayWeek = 7;
                    }
                    ca.add(Calendar.DATE, -dayWeek+1);
                    fromDate = sdf.format(ca.getTime());
                    break;
                case "month":
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
                    fromDate = sdf1.format(date)+"-01";
                    break;
                case "year":
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
                    fromDate = sdf2.format(date)+"-01-01";
                    break;
                default:break;
            }

            return fromDate;
        } catch (ParseException e) {
            return fromDate;
        }
    }
}
