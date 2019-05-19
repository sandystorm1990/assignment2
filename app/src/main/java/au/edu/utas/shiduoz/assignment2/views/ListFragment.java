package au.edu.utas.shiduoz.assignment2.views;

import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import au.edu.utas.shiduoz.assignment2.R;
import au.edu.utas.shiduoz.assignment2.data.Database;

public class ListFragment extends Fragment {

    int mYear, mMonth, mDay;

    TextView selectedDate;
    Button btn;
    final SpannableStringBuilder style = new SpannableStringBuilder();

    private TextView textView;
    public ListFragment() {
        //textView = getView().findViewById()
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View inflateView = inflater.inflate(R.layout.fragment_list, container, false);
        textView = inflateView.findViewById(R.id.testText);
        textView.setText("This is the list fragment");

        // connect to database
        Database databaseConnection = new Database(getActivity());
        final SQLiteDatabase db = databaseConnection.open();

        //datetime dialog
        //date shown in home page
        selectedDate = (TextView) inflateView.findViewById(R.id.dateInput);
        btn = (Button) inflateView.findViewById(R.id.dateBtn);

        // get current date
        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);

        selectedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
//                DialogFragment dateFragment = new SelectDateFragment();
//                dateFragment.show(getFragmentManager(), "DatePicker");
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
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
        //Date selectDate = sdf.parse(mYear+"-"+mMonth+"-"+mDay);
        String t2 = sdf.format(selectDate);
        //selectDate.setText(Html.fromHtml("<u>"+t2+"</u>"));
        selectedDate.setText(new StringBuffer().append(t2));
//        String str = mDay+"/"+mMonth+1+"/"+mYear;
//        today.setText(Html.fromHtml(str));
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
