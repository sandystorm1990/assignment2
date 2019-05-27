package au.edu.utas.shiduoz.assignment2.views;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import au.edu.utas.shiduoz.assignment2.R;
import au.edu.utas.shiduoz.assignment2.data.Database;
import au.edu.utas.shiduoz.assignment2.data.EntryTable;
import au.edu.utas.shiduoz.assignment2.models.Entry;
import au.edu.utas.shiduoz.assignment2.utils.Helper;

public class CreateActivity extends AppCompatActivity {

    Button addBtn, saveGoBtn;
    TextView dateInputCreate, selectDate, closeBtn;
    int mYear, mMonth, mDay;
    final int DATE_DIALOG = 1;

    public static String selectedMood="", selectedDate="";
    public static int selectedLevel=0;

    public static int entryId = 0;

    public static Entry mEntry = new Entry();


    // mood level
    TextView moodLevel1, moodLevel2, moodLevel3,moodLevel4,moodLevel5;
    // moods
    TextView moodHappy, moodSmile, moodAngry, moodBored, moodConfused,moodEmbarrassed, moodIll,
            moodSmart,moodSecret,moodNerd, moodSuspicious, moodKissing, moodLove, moodCrying, moodMad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        // get data from list activity
        Intent intent = getIntent();
        //entryId = intent.getIntExtra("id", 0);
        if (entryId > 0) {
            initData();
        }

        // date select
        selectDate = findViewById(R.id.dateSelectCreate);
        dateInputCreate = findViewById(R.id.dateInputCreate);
        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);

        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, YYYY-MM-dd");
        String t2 = "";
        if (selectedDate.length() == 0) {
            date = new Date();
            //sdf = new SimpleDateFormat("EEE, YYYY-MM-dd");
            t2 = sdf.format(date);
            dateInputCreate.setText(new StringBuffer().append(t2));
        } else {
            //date = new Date(selectedDate);
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            try {
                date = sdf1.parse(selectedDate);
                //Log.d("date","zz"+selectedDate);
                t2 = sdf.format(date);
            } catch ( ParseException e) {
                e.printStackTrace();
                return;
            }
        }

        //Date selectDate = sdf.parse(mYear+"-"+mMonth+"-"+mDay);
        dateInputCreate.setText(new StringBuffer().append(t2));
        // format date
        selectedDate = Helper.formatDate(mYear, mMonth, mDay);
        Log.d("ddd", selectedDate);

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
                // create or save operation
                if (createEntry(false, entryId)) {
                    Intent intent = new Intent(CreateActivity.this, DetailActivity.class);
                    // pass data to next activity
                    if (entryId > 0) {
                        //DetailActivity.mEntry = mEntry;
                        DetailActivity.entryId = entryId;
                        intent.putExtra("selectedActivity", mEntry.getmActivity());
                        intent.putExtra("selectedDescription", mEntry.getmDescription());
                        intent.putExtra("selectedLocation", mEntry.getmLocation());
                        intent.putExtra("selectedMedia", mEntry.getmMedia());
                        intent.putExtra("selectedWeather", mEntry.getmWeather());
                    } else {
                        intent.putExtra("selectedActivity", "");
                        intent.putExtra("selectedDescription", "");
                        intent.putExtra("selectedLocation", "");
                        intent.putExtra("selectedMedia", "");
                        intent.putExtra("selectedWeather", "");
                    }

                    intent.putExtra("selectedDate", selectedDate);
                    intent.putExtra("selectedLevel", selectedLevel);
                    intent.putExtra("selectedMood", selectedMood);

                    startActivity(intent);
                }

            }
        });
        // create action
        saveGoBtn = findViewById(R.id.saveGoBtn);
        saveGoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create or save operation
                if (createEntry(true, entryId)) {
                    Intent intent = new Intent(CreateActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        selectMoodLevel();
        selectMood();

        trigMoodLevel();
        trigMood();
    }

    public void initData()
    {
        //entryId = entry.getmId();
        selectedMood = mEntry.getmMood();
        selectedLevel = mEntry.getmMoodLevel();
        selectedDate = mEntry.getmDate();
        if (mEntry.getmWeather() == null) {
            mEntry.setmWeather("");
        }
        if (mEntry.getmMedia() == null) {
            mEntry.setmMedia("");
        }
        if (mEntry.getmDescription() == null) {
            mEntry.setmDescription("");
        }
        if (mEntry.getmLocation() == null) {
            mEntry.setmLocation("");
        }
        if (mEntry.getmActivity() == null) {
            mEntry.setmActivity("");
        }
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
        Date date = new Date(mYear-1900, mMonth, mDay);
        final SimpleDateFormat sdf = new SimpleDateFormat("EEE, YYYY-MM-dd");
        //Date selectDate = sdf.parse(mYear+"-"+mMonth+"-"+mDay);
        String t2 = sdf.format(date);
        //selectDate.setText(Html.fromHtml("<u>"+t2+"</u>"));
        dateInputCreate.setText(new StringBuffer().append(t2));
//        String str = mDay+"/"+mMonth+1+"/"+mYear;
//        today.setText(Html.fromHtml(str));
        // format date
        Helper helper = new Helper();
        selectedDate = Helper.formatDate(mYear, mMonth, mDay);
        Log.d("ddd", selectedDate);
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

    private boolean createEntry(boolean tag, int id)
    {
        //Log.d("qqq", selectedDate.length()+"");
        //Log.d("qqq", selectedLevel+"");
        //Log.d("qqq", selectedMood.length()+"");
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateActivity.this);
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        };
        // check date is selected or not
        if (selectedDate.length() == 0) {
            builder.setMessage("Please choose the date");
            builder.setNegativeButton("OK",dialogClickListener);
            AlertDialog dialog = builder.create();
            dialog.show();
            return false;
        }
        // check mood level is selected or not
        if (selectedLevel == 0) {
            builder.setMessage("Please choose your mood number from 1 to 5");
            builder.setNegativeButton("OK",dialogClickListener);
            AlertDialog dialog = builder.create();
            dialog.show();
            return false;
        }

        // check mood is selected or not
        if (selectedMood.length() == 0) {
            builder.setMessage("Please choose your mood");
            builder.setNegativeButton("OK",dialogClickListener);
            AlertDialog dialog = builder.create();
            dialog.show();
            return false;
        }

        if (tag) {
            // connect to database
            Database databaseConnection = new Database(this);
            final SQLiteDatabase db = databaseConnection.open();

            if (id > 0) {
                mEntry.setmId(id);
                mEntry.setmMood(selectedMood);
                mEntry.setmDate(selectedDate);
                mEntry.setmMoodLevel(selectedLevel);
                EntryTable.update(db, mEntry);
            } else {
                Entry entry = new Entry();
                entry.setmMood(selectedMood);
                entry.setmDate(selectedDate);
                entry.setmMoodLevel(selectedLevel);
                EntryTable.insert(db, entry);
            }
        }

        return true;
    }

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
                selectedLevel = 1;
                moodLevel1.setBackgroundColor(getResources().getColor(R.color.moodLevelBg));
            }
        });

        moodLevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodLevelBgColor();
                selectedLevel = 2;
                moodLevel2.setBackgroundColor(getResources().getColor(R.color.moodLevelBg));
            }
        });

        moodLevel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodLevelBgColor();
                selectedLevel = 3;
                moodLevel3.setBackgroundColor(getResources().getColor(R.color.moodLevelBg));
            }
        });

        moodLevel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodLevelBgColor();
                selectedLevel = 4;
                moodLevel4.setBackgroundColor(getResources().getColor(R.color.moodLevelBg));
            }
        });

        moodLevel5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodLevelBgColor();
                selectedLevel = 5;
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
        selectedLevel = 0;
    }

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
                selectedMood = "happy";
                moodHappy.setBackgroundColor(getResources().getColor(R.color.moodBg));
            }
        });

        moodSmile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodBgColor();
                selectedMood = "smile";
                moodSmile.setBackgroundColor(getResources().getColor(R.color.moodBg));
            }
        });

        moodAngry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodBgColor();
                selectedMood = "angry";
                moodAngry.setBackgroundColor(getResources().getColor(R.color.moodBg));
            }
        });

        moodBored.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodBgColor();
                selectedMood = "bored";
                moodBored.setBackgroundColor(getResources().getColor(R.color.moodBg));
            }
        });

        moodConfused.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodBgColor();
                selectedMood = "confused";
                moodConfused.setBackgroundColor(getResources().getColor(R.color.moodBg));
            }
        });

        moodCrying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodBgColor();
                selectedMood = "crying";
                moodCrying.setBackgroundColor(getResources().getColor(R.color.moodBg));
            }
        });

        moodEmbarrassed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodBgColor();
                selectedMood = "embarrassed";
                moodEmbarrassed.setBackgroundColor(getResources().getColor(R.color.moodBg));
            }
        });

        moodMad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodBgColor();
                selectedMood = "mad";
                moodMad.setBackgroundColor(getResources().getColor(R.color.moodBg));
            }
        });


        moodLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodBgColor();
                selectedMood = "love";
                moodLove.setBackgroundColor(getResources().getColor(R.color.moodBg));
            }
        });

        moodKissing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodBgColor();
                selectedMood = "kissing";
                moodKissing.setBackgroundColor(getResources().getColor(R.color.moodBg));
            }
        });


        moodSuspicious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodBgColor();
                selectedMood = "suspicious";
                moodSuspicious.setBackgroundColor(getResources().getColor(R.color.moodBg));
            }
        });

        moodIll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodBgColor();
                selectedMood = "ill";
                moodIll.setBackgroundColor(getResources().getColor(R.color.moodBg));
            }
        });

        moodSmart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodBgColor();
                selectedMood = "smart";
                moodSmart.setBackgroundColor(getResources().getColor(R.color.moodBg));
            }
        });

        moodNerd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodBgColor();
                selectedMood = "nerd";
                moodNerd.setBackgroundColor(getResources().getColor(R.color.moodBg));
            }
        });
        moodSecret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMoodBgColor();
                selectedMood = "secret";
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
        selectedMood = "";
    }

    private void trigMoodLevel()
    {
        switch (selectedLevel) {
            case 1:
                moodLevel1.performClick();
                break;
            case 2:
                moodLevel2.performClick();
                break;
            case 3:
                moodLevel3.performClick();
                break;
            case 4:
                moodLevel4.performClick();
                break;
            case 5:
                moodLevel5.performClick();
                break;
                default:break;
        }
    }

    private void trigMood()
    {
        switch (selectedMood) {
            case "happy":
                moodHappy.performClick();
                break;
            case "smile":
                moodSmile.performClick();
                break;
            case "angry":
                moodAngry.performClick();
                break;
            case "bored":
                moodBored.performClick();
                break;
            case "confused":
                moodConfused.performClick();
                break;

            case "crying":
                moodCrying.performClick();
                break;
            case "mad":
                moodMad.performClick();
                break;
            case "love":
                moodLove.performClick();
                break;
            case "kissing":
                moodKissing.performClick();
                break;
            case "nerd":
                moodNerd.performClick();
                break;

            case "embarrassed":
                moodEmbarrassed.performClick();
                break;
            case "suspicious":
                moodSuspicious.performClick();
                break;
            case "smart":
                moodSmart.performClick();
                break;
            case "secret":
                moodSecret.performClick();
                break;
            case "ill":
                moodIll.performClick();
                break;
            default:break;
        }
    }
}
