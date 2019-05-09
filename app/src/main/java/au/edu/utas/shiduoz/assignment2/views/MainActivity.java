package au.edu.utas.shiduoz.assignment2.views;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import au.edu.utas.shiduoz.assignment2.R;

public class MainActivity extends AppCompatActivity {

    int mYear, mMonth, mDay;
    final int DATE_DIALOG = 1;
    TextView selectedDate;
    Button btn;
    final SpannableStringBuilder style = new SpannableStringBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //date shown in home page
        selectedDate = (TextView) findViewById(R.id.dateInput);
        btn = (Button) findViewById(R.id.dateBtn);
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
        final Date todayDate = new Date();
        String t = sdf.format(todayDate);
        //dateInput.setText(Html.fromHtml("<u>"+"Today, "+t+"</u>"));
        selectedDate.setText(new StringBuffer().append(t));

        //date choice
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                showDialog(DATE_DIALOG);
            }
        });
        selectedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                showDialog(DATE_DIALOG);
            }
        });

        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG:
                return new DatePickerDialog(this, mdateListener, mYear, mMonth, mDay);
        }
        return null;
    }

    public void display() {
        Date selectDate = new Date(mYear-1900, mMonth, mDay);
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
        //Date selectDate = sdf.parse(mYear+"-"+mMonth+"-"+mDay);
        String t2 = sdf.format(selectDate);
        //selectDate.setText(Html.fromHtml("<u>"+t2+"</u>"));
        selectedDate.setText(new StringBuffer().append(t2));
        // 或者可以直接用字符串拼接
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
