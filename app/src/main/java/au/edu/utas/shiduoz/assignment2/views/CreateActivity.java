package au.edu.utas.shiduoz.assignment2.views;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
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

    Button addBtn, saveGoBtn;
    TextView selectDate, closeBtn;
    int mYear, mMonth, mDay;
    final int DATE_DIALOG = 1;
    final SpannableStringBuilder style = new SpannableStringBuilder();

    // mood level
    TextView moodLevel1, moodLevel2, moodLevel3,moodLevel4,moodLevel5;
    // moods
    TextView moodHappy, moodSmile, moodAngry;

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

        selectMoodLevel();
        selectMood();

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

    private void selectMoodLevel() {
        // setting tapped mood level
        moodLevel1 = findViewById(R.id.moodLevel1);
        moodLevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodLevelBgColor();
                moodLevel1.setBackgroundColor(getResources().getColor(R.color.moodLevelBg));
            }
        });
        moodLevel2 = findViewById(R.id.moodLevel2);
        moodLevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodLevelBgColor();
                moodLevel2.setBackgroundColor(getResources().getColor(R.color.moodLevelBg));
            }
        });
        moodLevel3 = findViewById(R.id.moodLevel3);
        moodLevel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodLevelBgColor();
                moodLevel3.setBackgroundColor(getResources().getColor(R.color.moodLevelBg));
            }
        });
        moodLevel4 = findViewById(R.id.moodLevel4);
        moodLevel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodLevelBgColor();
                moodLevel4.setBackgroundColor(getResources().getColor(R.color.moodLevelBg));
            }
        });
        moodLevel5 = findViewById(R.id.moodLevel5);
        moodLevel5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodLevelBgColor();
                moodLevel5.setBackgroundColor(getResources().getColor(R.color.moodLevelBg));
            }
        });
    }

    private void clearMoodLevelBgColor() {
        moodLevel1.setBackgroundColor(Color.TRANSPARENT);
        moodLevel2.setBackgroundColor(Color.TRANSPARENT);
        moodLevel3.setBackgroundColor(Color.TRANSPARENT);
        moodLevel4.setBackgroundColor(Color.TRANSPARENT);
        moodLevel5.setBackgroundColor(Color.TRANSPARENT);
    }

    //
    private void selectMood() {
        moodHappy = findViewById(R.id.moodHappy);
        moodHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moodHappy.setBackgroundColor(getResources().getColor(R.color.moodBg));
            }
        });

        moodSmile = findViewById(R.id.moodSmile);
        moodSmile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moodSmile.setBackgroundColor(getResources().getColor(R.color.moodBg));
            }
        });

        moodAngry = findViewById(R.id.moodAngry);
        moodAngry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moodAngry.setBackgroundColor(getResources().getColor(R.color.moodBg));
            }
        });

    }
}
