package au.edu.utas.shiduoz.assignment2.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import au.edu.utas.shiduoz.assignment2.models.Entry;

public class EntryTable {
    public static final String TABLE_NAME      = "mt_entry";
    public static final String KEY_ENTRY_ID    = "id";
    public static final String KEY_WEATHER     = "weather";
    public static final String KEY_MOOD        = "mood";
    public static final String KEY_MOOD_LEVEL  = "level";
    public static final String KEY_ACTIVITY    = "activity";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_MEDIA       = "media";
    public static final String KEY_LOCATION    = "location";
    public static final String KEY_DATE        = "date";
    public static final String KEY_CREATE      = "created_at";
    public static final String KEY_UPDATE      = "updated_at";

    // create table sql
    public static final String CREATE_STATEMENT = "CREATE TABLE "
            + TABLE_NAME
            + " (" + KEY_ENTRY_ID + " integer primary key autoincrement, "
            + KEY_MOOD + " string not null, "
            + KEY_MOOD_LEVEL + " integer not null, "
            + KEY_ACTIVITY + " string default null, "
            + KEY_WEATHER + " string default null , "
            //+ KEY_ACTIVITY + " string default null, "
            + KEY_DESCRIPTION + " text default null, "
            + KEY_MEDIA + " string default not null default '', "
            + KEY_LOCATION + " string default null, "
            + KEY_DATE + " date, "
            + KEY_CREATE + " datetime, "
            + KEY_UPDATE + " datetime "
            + ");";

    /**
     * insert new data
     *
     * @param db
     * @param entry
     */
    public static void insert(SQLiteDatabase db , Entry entry)
    {
        ContentValues values = new ContentValues();
        values.put(KEY_MOOD, entry.getmMood());
        values.put(KEY_MOOD_LEVEL, entry.getmMoodLevel());
        values.put(KEY_ACTIVITY, entry.getmActivity());
        values.put(KEY_WEATHER, entry.getmWeather());
        values.put(KEY_DESCRIPTION,entry.getmDescription());
        values.put(KEY_LOCATION, entry.getmLocation());
        values.put(KEY_MEDIA, entry.getmMedia());
        values.put(KEY_DATE, entry.getmDate());
        // datetime
        Date nowDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        values.put(KEY_CREATE, sdf.format(nowDate));
        values.put(KEY_UPDATE, sdf.format(nowDate));
        db.insert(TABLE_NAME, null, values);
    }

    /**
     * select all data from database
     *
     * @param db
     * @return
     */
    public static ArrayList<Entry> selectAll(SQLiteDatabase db)
    {
        ArrayList<Entry> results = new ArrayList<Entry>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                Entry entry = createFromCursor(c);
                results.add(entry);
                c.moveToNext();
            }
        }

        return  results;
    }

    /**
     * select all data from database
     *
     * @param db
     * @return
     */
    public static ArrayList<Entry> selectByDate(SQLiteDatabase db, String date)
    {
        ArrayList<Entry> results = new ArrayList<Entry>();
        Cursor c = db.query(TABLE_NAME, null, KEY_DATE+"='"+date+"'", null, null, null, null);
        if (c != null) {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                Entry entry = createFromCursor(c);
                results.add(entry);
                c.moveToNext();
            }
        }

        return  results;
    }

    /**
     * create Entry from cursor
     *
     * @param c
     * @return
     */
    public static Entry createFromCursor (Cursor c)
    {
        if (c == null || c.isAfterLast() || c.isBeforeFirst()) {
            return  null;
        } else {
            Entry p = new Entry();
            p.setmId(c.getInt(c.getColumnIndex(KEY_ENTRY_ID)));
            p.setmMood(c.getString(c.getColumnIndex(KEY_MOOD)));
            p.setmMoodLevel(c.getInt(c.getColumnIndex(KEY_MOOD_LEVEL)));
            p.setmActivity(c.getString(c.getColumnIndex(KEY_ACTIVITY)));
            p.setmDescription(c.getString(c.getColumnIndex(KEY_DESCRIPTION)));
            p.setmWeather(c.getString(c.getColumnIndex(KEY_WEATHER)));
            p.setmLocation(c.getString(c.getColumnIndex(KEY_LOCATION)));
            p.setmMedia(c.getString(c.getColumnIndex(KEY_MEDIA)));
            p.setmDate(c.getString(c.getColumnIndex(KEY_DATE)));
            p.setmCreate(c.getString(c.getColumnIndex(KEY_CREATE)));
            p.setmUpdate(c.getString(c.getColumnIndex(KEY_UPDATE)));

            return p;
        }
    }

    /**
     * retrieve data by id
     * @param db
     * @param id
     * @return
     */
    public static Entry getByID (SQLiteDatabase db, int id)
    {
        Cursor c = db.query(
                TABLE_NAME,
                null,
                KEY_ENTRY_ID + "=" + id,
                null,
                null,
                null,
                null
        );

        return createFromCursor(c);
    }

    /**
     * update
     * @param db
     * @param entry
     */
    public static void update (SQLiteDatabase db, Entry entry)
    {
        ContentValues values = new ContentValues();
        values.put(KEY_MOOD, entry.getmMood());
        values.put(KEY_MOOD_LEVEL, entry.getmMoodLevel());
        values.put(KEY_ACTIVITY, entry.getmActivity());
        values.put(KEY_WEATHER, entry.getmWeather());
        values.put(KEY_DESCRIPTION, entry.getmDescription());
        values.put(KEY_LOCATION, entry.getmLocation());
        values.put(KEY_MEDIA, entry.getmMedia());
        values.put(KEY_DATE, entry.getmDate());

        Date nowDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        values.put(KEY_UPDATE, sdf.format(nowDate));
        db.update(TABLE_NAME, values, KEY_ENTRY_ID+ "= ?", new String[] {""+entry.getmId()});
    }

    //
    public static void remove (SQLiteDatabase db, int id)
    {
        //String[] args = {String.valueOf(id)};
        db.delete(TABLE_NAME, KEY_ENTRY_ID+"=?", new String[]{id+""});
    }
}
