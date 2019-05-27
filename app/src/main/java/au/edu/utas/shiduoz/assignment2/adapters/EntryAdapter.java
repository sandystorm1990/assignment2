package au.edu.utas.shiduoz.assignment2.adapters;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

import au.edu.utas.shiduoz.assignment2.R;
import au.edu.utas.shiduoz.assignment2.data.Database;
import au.edu.utas.shiduoz.assignment2.data.EntryTable;
import au.edu.utas.shiduoz.assignment2.models.Entry;
import au.edu.utas.shiduoz.assignment2.utils.Helper;

public class EntryAdapter extends ArrayAdapter<Entry> {
    private int mLayoutResourceID;
    private  Context mContext;

    private SQLiteDatabase db;

    private TextView removeItem, shareItem;
    AlertDialog.Builder builder;

    LinearLayout ll;
    //ViewGroup.LayoutParams lp;
    List<Entry> mEntries;

    public EntryAdapter(Context context, int resource, List<Entry> objects)
    {
        super(context, resource, objects);
        this.mLayoutResourceID = resource;
        this.mContext = context;
        mEntries = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        LayoutInflater layoutInflater =
                (LayoutInflater)getContext().getSystemService(Service.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(mLayoutResourceID, parent, false);
        ll = row.findViewById(R.id.imgeParent);

        // alert dialog
        builder = new AlertDialog.Builder(mContext);

        final Entry entry = this.getItem(position);
        //Log.d("zsd", android.R.id.text1+"");
        // get entry item
        TextView itemDate = row.findViewById(R.id.itemDate);
        TextView itemMood = row.findViewById(R.id.itemMood);
        TextView itemLevel = row.findViewById(R.id.itemLevel);
        TextView itemWeather = row.findViewById(R.id.itemWeather);
        TextView itemLocation = row.findViewById(R.id.itemLocation);
        TextView itemDescription = row.findViewById(R.id.itemDesc);
        TextView itemActivity = row.findViewById(R.id.itemActivity);
        //ImageView itemMedia = row.findViewById(R.id.itemImage);
        itemDate.setText(entry.getmUpdate());
        //itemLevel.setText(entry.getmMoodLevel()+"");
        getMoodLevelIcon(itemLevel, entry.getmMoodLevel());
        itemMood.setText(entry.getmMood());
        getMoodIcon(itemMood, entry.getmMood());

        String weather = entry.getmWeather();
        if (weather != null) {
            itemWeather.setText(entry.getmWeather());
            getWeatherIcon(itemWeather, weather);
        }
        String location = entry.getmLocation();
        if (location != null) {
            itemLocation.setText(entry.getmLocation());
        }
        String description = entry.getmDescription();
        if (description != null) {
            itemDescription.setText(entry.getmDescription());
        }
        String activity = entry.getmActivity();
        if (activity != null) {
            itemActivity.setText(activity);
            getActivityIcon(itemActivity, activity);
        }
        String media = entry.getmMedia(); Log.d("media", media+"zzsd");
        if (media != null && media.length() > 1) {
            ImageView image = new ImageView(getContext());
            //image.setLayoutParams(new LayoutParams(lp.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            ll.addView(image);
            Helper.showPic(ll, image, media);
        }

        removeItem = row.findViewById(R.id.itemRemove);
        shareItem = row.findViewById(R.id.itemShare);
        // remove action
        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // popup dialog to warn
                //AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("Are you sure to remove it?");

                // cancel
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                // update
                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Database databaseConnection = new Database(getContext());
                        db = databaseConnection.open();
                        EntryTable.remove(db, entry.getmId());
                        Log.d("zsd","remove"+entry.getmId());
                        mEntries.remove(position);

                        notifyDataSetChanged();
                    }
                });

                builder.create().show();
                //builder.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
                //builder.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.BLACK);

                //Log.d("zsd","remove"+entry.getmId());
            }
        });
        // share action
        shareItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // share action
                shareEntry(entry);
                Log.d("zsd","share");
            }
        });

        return  row;
    }

    /**
     * share entry to...
     *
     * @param entry
     */
    private void shareEntry(Entry entry)
    {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Date: "+entry.getmDate()+";Mood: "+entry.getmMood()+"; Level:"+entry.getmMoodLevel());
        //shareIntent.setType("image/jpeg");
        shareIntent.setType("text/plain");
        getContext().startActivity(Intent.createChooser(shareIntent, "Share Via..."));
    }

    private void getWeatherIcon(TextView textView, String weather)
    {
        switch (weather) {
            case "cloudy":
                Drawable drawable1 = mContext.getResources().getDrawable(R.mipmap.icon_cloudy);
                drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
                textView.setCompoundDrawables(null, null, drawable1, null);
                break;
            case "sunny":
                Drawable drawable2 = mContext.getResources().getDrawable(R.mipmap.icon_sunny);
                drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
                textView.setCompoundDrawables(null, null, drawable2, null);
                break;
            case "rainy":
                Drawable drawable3 = mContext.getResources().getDrawable(R.mipmap.icon_rainy);
                drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
                textView.setCompoundDrawables(null, null, drawable3, null);
                break;
            case "snowy":
                Drawable drawable4 = mContext.getResources().getDrawable(R.mipmap.icon_snowy);
                drawable4.setBounds(0, 0, drawable4.getMinimumWidth(), drawable4.getMinimumHeight());
                textView.setCompoundDrawables(null, null, drawable4, null);
                break;
            case "sunnyCloudy":
                Drawable drawable5 = mContext.getResources().getDrawable(R.mipmap.icon_sun_cloudy);
                drawable5.setBounds(0, 0, drawable5.getMinimumWidth(), drawable5.getMinimumHeight());
                textView.setCompoundDrawables(null, null, drawable5, null);
                break;
            case "thunder":
                Drawable drawable6 = mContext.getResources().getDrawable(R.mipmap.icon_thunder);
                drawable6.setBounds(0, 0, drawable6.getMinimumWidth(), drawable6.getMinimumHeight());
                textView.setCompoundDrawables(null, null, drawable6, null);
                break;
            case "windy":
                Drawable drawable7 = mContext.getResources().getDrawable(R.mipmap.icon_windy);
                drawable7.setBounds(0, 0, drawable7.getMinimumWidth(), drawable7.getMinimumHeight());
                textView.setCompoundDrawables(null, null, drawable7, null);
                break;

                default:break;
        }

    }

    private void getMoodLevelIcon(TextView textView, int level)
    {
        switch (level) {
            case 1:
                Drawable drawable1 = mContext.getResources().getDrawable(R.mipmap.icon_number_1);
                drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
                textView.setCompoundDrawables(null, drawable1, null, null);
                break;
            case 2:
                Drawable drawable2 = mContext.getResources().getDrawable(R.mipmap.icon_number_2);
                drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
                textView.setCompoundDrawables(null, drawable2, null, null);
                break;
            case 3:
                Drawable drawable3 = mContext.getResources().getDrawable(R.mipmap.icon_number_3);
                drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
                textView.setCompoundDrawables(null, drawable3, null, null);
                break;
            case 4:
                Drawable drawable4 = mContext.getResources().getDrawable(R.mipmap.icon_number_4);
                drawable4.setBounds(0, 0, drawable4.getMinimumWidth(), drawable4.getMinimumHeight());
                textView.setCompoundDrawables(null, drawable4, null, null);
                break;
            case 5:
                Drawable drawable5 = mContext.getResources().getDrawable(R.mipmap.icon_number_5);
                drawable5.setBounds(0, 0, drawable5.getMinimumWidth(), drawable5.getMinimumHeight());
                textView.setCompoundDrawables(null, drawable5, null, null);
                break;

                default:break;
        }
    }

    private void getMoodIcon(TextView textView, String mood)
    {
        switch (mood) {
            case "happy":
                Drawable drawable1 = mContext.getResources().getDrawable(R.mipmap.icon_happy);
                drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
                textView.setCompoundDrawables(null, drawable1, null, null);
                break;
            case "angry":
                Drawable drawable2 = mContext.getResources().getDrawable(R.mipmap.icon_angry);
                drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
                textView.setCompoundDrawables(null, drawable2, null, null);
                break;
            case "smile":
                Drawable drawable3 = mContext.getResources().getDrawable(R.mipmap.icon_smile);
                drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
                textView.setCompoundDrawables(null, drawable3, null, null);
                break;
            case "bored":
                Drawable drawable4 = mContext.getResources().getDrawable(R.mipmap.icon_bored);
                drawable4.setBounds(0, 0, drawable4.getMinimumWidth(), drawable4.getMinimumHeight());
                textView.setCompoundDrawables(null, drawable4, null, null);
                break;
            case "confused":
                Drawable drawable5 = mContext.getResources().getDrawable(R.mipmap.icon_confused);
                drawable5.setBounds(0, 0, drawable5.getMinimumWidth(), drawable5.getMinimumHeight());
                textView.setCompoundDrawables(null, drawable5, null, null);
                break;
            case "embarrassed":
                Drawable drawable6 = mContext.getResources().getDrawable(R.mipmap.icon_embarrassed);
                drawable6.setBounds(0, 0, drawable6.getMinimumWidth(), drawable6.getMinimumHeight());
                textView.setCompoundDrawables(null, drawable6, null, null);
                break;
            case "ill":
                Drawable drawable7 = mContext.getResources().getDrawable(R.mipmap.icon_ill);
                drawable7.setBounds(0, 0, drawable7.getMinimumWidth(), drawable7.getMinimumHeight());
                textView.setCompoundDrawables(null, drawable7, null, null);
                break;
            case "mad":
                Drawable drawable8 = mContext.getResources().getDrawable(R.mipmap.icon_mad);
                drawable8.setBounds(0, 0, drawable8.getMinimumWidth(), drawable8.getMinimumHeight());
                textView.setCompoundDrawables(null, drawable8, null, null);
                break;
            case "secret":
                Drawable drawable9 = mContext.getResources().getDrawable(R.mipmap.icon_secret);
                drawable9.setBounds(0, 0, drawable9.getMinimumWidth(), drawable9.getMinimumHeight());
                textView.setCompoundDrawables(null, drawable9, null, null);
                break;
            case "suspicious":
                Drawable drawable10 = mContext.getResources().getDrawable(R.mipmap.icon_suspicious);
                drawable10.setBounds(0, 0, drawable10.getMinimumWidth(), drawable10.getMinimumHeight());
                textView.setCompoundDrawables(null, drawable10, null, null);
                break;
            case "crying":
                Drawable drawable11 = mContext.getResources().getDrawable(R.mipmap.icon_crying);
                drawable11.setBounds(0, 0, drawable11.getMinimumWidth(), drawable11.getMinimumHeight());
                textView.setCompoundDrawables(null, drawable11, null, null);
                break;
            case "kissing":
                Drawable drawable12 = mContext.getResources().getDrawable(R.mipmap.icon_kissing);
                drawable12.setBounds(0, 0, drawable12.getMinimumWidth(), drawable12.getMinimumHeight());
                textView.setCompoundDrawables(null, drawable12, null, null);
                break;
            case "love":
                Drawable drawable13 = mContext.getResources().getDrawable(R.mipmap.icon_love);
                drawable13.setBounds(0, 0, drawable13.getMinimumWidth(), drawable13.getMinimumHeight());
                textView.setCompoundDrawables(null, drawable13, null, null);
                break;
            case "nerd":
                Drawable drawable14 = mContext.getResources().getDrawable(R.mipmap.icon_nerd);
                drawable14.setBounds(0, 0, drawable14.getMinimumWidth(), drawable14.getMinimumHeight());
                textView.setCompoundDrawables(null, drawable14, null, null);
                break;
            case "smart":
                Drawable drawable15 = mContext.getResources().getDrawable(R.mipmap.icon_smart);
                drawable15.setBounds(0, 0, drawable15.getMinimumWidth(), drawable15.getMinimumHeight());
                textView.setCompoundDrawables(null, drawable15, null, null);
                break;

                default: break;

        }
    }

    private void getActivityIcon(TextView textView, String activity)
    {
        switch (activity) {
            case "reading":
                Drawable drawable1 = mContext.getResources().getDrawable(R.mipmap.icon_reading);
                drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
                textView.setCompoundDrawables(drawable1, null,null, null);
                break;
            case "driving":
                Drawable drawable2 = mContext.getResources().getDrawable(R.mipmap.icon_driving);
                drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
                textView.setCompoundDrawables(drawable2, null, null, null);
                break;
            case "dinner":
                Drawable drawable3 = mContext.getResources().getDrawable(R.mipmap.icon_dinner);
                drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
                textView.setCompoundDrawables(drawable3, null, null, null);
                break;
            case "bathing":
                Drawable drawable4 = mContext.getResources().getDrawable(R.mipmap.icon_bathing);
                drawable4.setBounds(0, 0, drawable4.getMinimumWidth(), drawable4.getMinimumHeight());
                textView.setCompoundDrawables(drawable4, null, null, null);
                break;
            case "dating":
                Drawable drawable5 = mContext.getResources().getDrawable(R.mipmap.icon_dating);
                drawable5.setBounds(0, 0, drawable5.getMinimumWidth(), drawable5.getMinimumHeight());
                textView.setCompoundDrawables(drawable5, null, null, null);
                break;
            case "shopping":
                Drawable drawable6 = mContext.getResources().getDrawable(R.mipmap.icon_shopping);
                drawable6.setBounds(0, 0, drawable6.getMinimumWidth(), drawable6.getMinimumHeight());
                textView.setCompoundDrawables(drawable6, null, null, null);
                break;
            case "sleeping":
                Drawable drawable7 = mContext.getResources().getDrawable(R.mipmap.icon_sleeping);
                drawable7.setBounds(0, 0, drawable7.getMinimumWidth(), drawable7.getMinimumHeight());
                textView.setCompoundDrawables(drawable7, null, null, null);
                break;
            case "drinking":
                Drawable drawable8 = mContext.getResources().getDrawable(R.mipmap.icon_drinking);
                drawable8.setBounds(0, 0, drawable8.getMinimumWidth(), drawable8.getMinimumHeight());
                textView.setCompoundDrawables(drawable8, null, null, null);
                break;
            case "gaming":
                Drawable drawable9 = mContext.getResources().getDrawable(R.mipmap.icon_gaming);
                drawable9.setBounds(0, 0, drawable9.getMinimumWidth(), drawable9.getMinimumHeight());
                textView.setCompoundDrawables(drawable9, null, null, null);
                break;
            case "meeting":
                Drawable drawable10 = mContext.getResources().getDrawable(R.mipmap.icon_meeting);
                drawable10.setBounds(0, 0, drawable10.getMinimumWidth(), drawable10.getMinimumHeight());
                textView.setCompoundDrawables(drawable10, null, null, null);
                break;
            case "movie":
                Drawable drawable11 = mContext.getResources().getDrawable(R.mipmap.icon_movie);
                drawable11.setBounds(0, 0, drawable11.getMinimumWidth(), drawable11.getMinimumHeight());
                textView.setCompoundDrawables(drawable11, null, null, null);
                break;
            case "music":
                Drawable drawable12 = mContext.getResources().getDrawable(R.mipmap.icon_music);
                drawable12.setBounds(0, 0, drawable12.getMinimumWidth(), drawable12.getMinimumHeight());
                textView.setCompoundDrawables(drawable12, null, null, null);
                break;
            case "tea":
                Drawable drawable13 = mContext.getResources().getDrawable(R.mipmap.icon_tea);
                drawable13.setBounds(0, 0, drawable13.getMinimumWidth(), drawable13.getMinimumHeight());
                textView.setCompoundDrawables(drawable13, null, null, null);
                break;
            case "working":
                Drawable drawable14 = mContext.getResources().getDrawable(R.mipmap.icon_working);
                drawable14.setBounds(0, 0, drawable14.getMinimumWidth(), drawable14.getMinimumHeight());
                textView.setCompoundDrawables(drawable14, null, null, null);
                break;
            case "tv":
                Drawable drawable15 = mContext.getResources().getDrawable(R.mipmap.icon_tv);
                drawable15.setBounds(0, 0, drawable15.getMinimumWidth(), drawable15.getMinimumHeight());
                textView.setCompoundDrawables(drawable15, null, null, null);
                break;

            default: break;

        }
    }
}
