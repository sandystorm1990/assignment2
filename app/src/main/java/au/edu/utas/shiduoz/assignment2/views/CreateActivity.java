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
    TextView dateInputCreate, selectDate, closeBtn;
    int mYear, mMonth, mDay;
    final int DATE_DIALOG = 1;
    final SpannableStringBuilder style = new SpannableStringBuilder();

    // mood level
    TextView moodLevel1, moodLevel2, moodLevel3,moodLevel4,moodLevel5;
    // moods
    TextView moodHappy, moodSmile, moodAngry, moodBored, moodConfused,moodEmbarrassed, moodIll,
            moodSmart,moodSecret,moodNerd, moodSuspicious, moodKissing, moodLove, moodCrying, moodMad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        // date select
        selectDate = findViewById(R.id.dateSelectCreate);
        dateInputCreate = findViewById(R.id.dateInputCreate);
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
        dateInputCreate.setText(new StringBuffer().append(t2));
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
        moodLevel2 = findViewById(R.id.moodLevel2);
        moodLevel3 = findViewById(R.id.moodLevel3);
        moodLevel4 = findViewById(R.id.moodLevel4);
        moodLevel5 = findViewById(R.id.moodLevel5);

        moodLevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodLevelBgColor();
                moodLevel1.setBackgroundColor(getResources().getColor(R.color.moodLevelBg));
            }
        });

        moodLevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodLevelBgColor();
                moodLevel2.setBackgroundColor(getResources().getColor(R.color.moodLevelBg));
            }
        });

        moodLevel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodLevelBgColor();
                moodLevel3.setBackgroundColor(getResources().getColor(R.color.moodLevelBg));
            }
        });

        moodLevel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodLevelBgColor();
                moodLevel4.setBackgroundColor(getResources().getColor(R.color.moodLevelBg));
            }
        });

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
        moodSmile = findViewById(R.id.moodSmile);
        moodAngry = findViewById(R.id.moodAngry);
        moodBored = findViewById(R.id.moodBored);
        moodConfused = findViewById(R.id.moodConfused);
        moodCrying = findViewById(R.id.moodCrying);
        moodEmbarrassed = findViewById(R.id.moodEmbarrassed);
        moodMad = findViewById(R.id.moodMad);
        moodLove = findViewById(R.id.moodLove);
        moodKissing = findViewById(R.id.moodKissing);
        moodSuspicious = findViewById(R.id.moodSuspicious);
        moodIll = findViewById(R.id.moodIll);
        moodSmart = findViewById(R.id.moodSmart);
        moodNerd = findViewById(R.id.moodNerd);
        moodSecret = findViewById(R.id.moodSecret);

        moodHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodBgColor();
                moodHappy.setBackgroundColor(getResources().getColor(R.color.moodBg));
            }
        });

        moodSmile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodBgColor();
                moodSmile.setBackgroundColor(getResources().getColor(R.color.moodBg));
            }
        });

        moodAngry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodBgColor();
                moodAngry.setBackgroundColor(getResources().getColor(R.color.moodBg));
            }
        });

        moodBored.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodBgColor();
                moodBored.setBackgroundColor(getResources().getColor(R.color.moodBg));
            }
        });

        moodConfused.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodBgColor();
                moodConfused.setBackgroundColor(getResources().getColor(R.color.moodBg));
            }
        });

        moodCrying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodBgColor();
                moodCrying.setBackgroundColor(getResources().getColor(R.color.moodBg));
            }
        });

        moodEmbarrassed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodBgColor();
                moodEmbarrassed.setBackgroundColor(getResources().getColor(R.color.moodBg));
            }
        });

        moodMad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodBgColor();
                moodMad.setBackgroundColor(getResources().getColor(R.color.moodBg));
            }
        });


        moodLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodBgColor();
                moodLove.setBackgroundColor(getResources().getColor(R.color.moodBg));
            }
        });

        moodKissing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodBgColor();
                moodKissing.setBackgroundColor(getResources().getColor(R.color.moodBg));
            }
        });


        moodSuspicious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodBgColor();
                moodSuspicious.setBackgroundColor(getResources().getColor(R.color.moodBg));
            }
        });

        moodIll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodBgColor();
                moodIll.setBackgroundColor(getResources().getColor(R.color.moodBg));
            }
        });

        moodSmart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodBgColor();
                moodSmart.setBackgroundColor(getResources().getColor(R.color.moodBg));
            }
        });

        moodNerd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodBgColor();
                moodNerd.setBackgroundColor(getResources().getColor(R.color.moodBg));
            }
        });
        moodSecret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodBgColor();
                moodSecret.setBackgroundColor(getResources().getColor(R.color.moodBg));
            }
        });
    }

    private void clearMoodBgColor() {
        moodAngry.setBackgroundColor(Color.TRANSPARENT);
        moodHappy.setBackgroundColor(Color.TRANSPARENT);
        moodSmile.setBackgroundColor(Color.TRANSPARENT);
        moodBored.setBackgroundColor(Color.TRANSPARENT);
        moodConfused.setBackgroundColor(Color.TRANSPARENT);
        moodCrying.setBackgroundColor(Color.TRANSPARENT);
        moodEmbarrassed.setBackgroundColor(Color.TRANSPARENT);
        moodMad.setBackgroundColor(Color.TRANSPARENT);
        moodLove.setBackgroundColor(Color.TRANSPARENT);
        moodKissing.setBackgroundColor(Color.TRANSPARENT);
        moodNerd.setBackgroundColor(Color.TRANSPARENT);
        moodSecret.setBackgroundColor(Color.TRANSPARENT);
        moodSuspicious.setBackgroundColor(Color.TRANSPARENT);
        moodIll.setBackgroundColor(Color.TRANSPARENT);
        moodSmart.setBackgroundColor(Color.TRANSPARENT);
    }
}
