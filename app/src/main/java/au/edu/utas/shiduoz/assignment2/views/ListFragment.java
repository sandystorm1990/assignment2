package au.edu.utas.shiduoz.assignment2.views;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import au.edu.utas.shiduoz.assignment2.R;
import au.edu.utas.shiduoz.assignment2.adapters.EntryAdapter;
import au.edu.utas.shiduoz.assignment2.data.Database;
import au.edu.utas.shiduoz.assignment2.data.EntryTable;
import au.edu.utas.shiduoz.assignment2.models.Entry;
import au.edu.utas.shiduoz.assignment2.utils.Helper;

public class ListFragment extends Fragment {

    int mYear, mMonth, mDay;

    TextView selectedDate, dateInput;

    public ListFragment() { }

    private View mInflateView;
    private String formatDate;
    private SQLiteDatabase db;

    ArrayList<Entry> mEntries;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        final View inflateView = inflater.inflate(R.layout.fragment_list, container, false);

        mInflateView = inflateView;

        //date shown in home page
        selectedDate = (TextView) inflateView.findViewById(R.id.dateSelect);
        dateInput = inflateView.findViewById(R.id.dateInput);

        // get current date
        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);

        Date selectDate = new Date();
        //final SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd/MM/YYYY");
        final SimpleDateFormat sdf = new SimpleDateFormat("EEE, YYYY-MM-dd");
        //Date selectDate = sdf.parse(mYear+"-"+mMonth+"-"+mDay);
        String t2 = sdf.format(selectDate);
        //selectDate.setText(Html.fromHtml("<u>"+t2+"</u>"));
        dateInput.setText(new StringBuffer().append(t2));
        formatDate = Helper.formatDate(mYear, mMonth, mDay);

        // connect to database
        Database databaseConnection = new Database(getActivity());
        db = databaseConnection.open();
        mEntries = EntryTable.selectByDate(db, formatDate);
        //Log.d("zzz", formatDate);
        // have no entry
        if (mEntries.size() == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Do not have entry today?");
            //cancel
            builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            // update
            builder.setPositiveButton("Go to Create", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(getActivity(), CreateActivity.class);
                    startActivity(intent);
                }
            });
            builder.create().show();
        }
        for (Entry entry: mEntries
        ) {
            Log.d("zsd", entry.getmMedia()+"qweq");
        }
        ListView entryList = inflateView.findViewById(R.id.entryList);
        final EntryAdapter entryAdapter = new EntryAdapter(getActivity(), R.layout.list_item, mEntries);
        entryList.setAdapter(entryAdapter);

        //item tapped
        entryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                final Entry entry = mEntries.get(i);

                Intent intent = new Intent(getActivity(), CreateActivity.class);
                // pass data to edit
                CreateActivity.mEntry = entry;
                CreateActivity.entryId = entry.getmId();
                //intent.putExtra("id", entry.getmId());
                startActivity(intent);
            }
        });

        selectedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                // show date dialog
                DatePickerDialog d = new DatePickerDialog(getActivity(), mdateListener, mYear, mMonth, mDay);
                d.show();
            }
        });

        return inflateView;

        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * display date
     */
    public void display() {
        Date selectDate = new Date(mYear-1900, mMonth, mDay);
        final SimpleDateFormat sdf = new SimpleDateFormat("EEE, YYYY-MM-dd");
        //Date selectDate = sdf.parse(mYear+"-"+mMonth+"-"+mDay);
        String t2 = sdf.format(selectDate);
        //selectDate.setText(Html.fromHtml("<u>"+t2+"</u>"));
        dateInput.setText(new StringBuffer().append(t2));
//        String str = mDay+"/"+mMonth+1+"/"+mYear;
//        today.setText(Html.fromHtml(str));

        formatDate = Helper.formatDate(mYear, mMonth, mDay);

        //select entry by date
        mEntries = EntryTable.selectByDate(db, formatDate);
        ListView entryList = mInflateView.findViewById(R.id.entryList);
        final EntryAdapter entryAdapter = new EntryAdapter(getActivity(), R.layout.list_item, mEntries);
        entryList.setAdapter(entryAdapter);
    }

    private DatePickerDialog.OnDateSetListener mdateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            display();
        }
    };
}
