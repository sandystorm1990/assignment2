package au.edu.utas.shiduoz.assignment2.views;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import au.edu.utas.shiduoz.assignment2.R;

public class CreateActivity extends AppCompatActivity {

    Button closeBtn, addBtn, saveGoBtn;
    TextView selectDate;
    int mYear, mMonth, mDay;
    final int DATE_DIALOG = 1;
    final SpannableStringBuilder style = new SpannableStringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        // date select
        selectDate = findViewById(R.id.dateInputCreate);
        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG);
            }
        });

        // close
        closeBtn = findViewById(R.id.closeBtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        // create action & add detail
        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // todo create or save operation
                Intent intent = new Intent(CreateActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });
        // create action
        saveGoBtn = findViewById(R.id.saveGoBtn);
        saveGoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // todo create or save operation
                Intent intent = new Intent(CreateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG:
                return new DatePickerDialog(this, mdateListener, mYear, mMonth, mDay);
        }
        return null;
    }

    public void display() {
        Date selectedDate = new Date(mYear-1900, mMonth, mDay);
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
        //Date selectDate = sdf.parse(mYear+"-"+mMonth+"-"+mDay);
        String t2 = sdf.format(selectedDate);
        //selectDate.setText(Html.fromHtml("<u>"+t2+"</u>"));
        selectDate.setText(new StringBuffer().append(t2));
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
