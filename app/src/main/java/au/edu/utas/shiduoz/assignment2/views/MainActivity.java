package au.edu.utas.shiduoz.assignment2.views;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import au.edu.utas.shiduoz.assignment2.data.Database;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    int mYear, mMonth, mDay;
    final int DATE_DIALOG = 1;
    TextView selectedDate;
    Button btn;
    final SpannableStringBuilder style = new SpannableStringBuilder();

    // ui object
    private TextView listTab;
    private TextView createTab;
    private TextView statTab;

    private ListFragment listFragment;
    private StatFragment statFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // connect to database
        Database databaseConnection = new Database(this);
        final SQLiteDatabase db = databaseConnection.open();

        listTab = findViewById(R.id.listTab);
        createTab = findViewById(R.id.createTab);
        statTab = findViewById(R.id.statTab);
        listTab.setOnClickListener(this);
        createTab.setOnClickListener(this);
        statTab.setOnClickListener(this);

        fragmentManager = getSupportFragmentManager();
        listTab.performClick();






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

        //
        listTab = findViewById(R.id.listTab);
        statTab = findViewById(R.id.statTab);
        createTab = findViewById(R.id.createTab);
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

    //

    @Override
    public void onClick(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();//只能是局部变量，不能为全局变量，否则不能重复commit
        //FragmentTransaction只能使用一次
        hideAllFragment(fragmentTransaction);
        switch (view.getId()){
            case R.id.listTab:
                setAllFalse();
                listTab.setSelected(true);
                if (listFragment==null){
                    listFragment=new ListFragment();
                    fragmentTransaction.add(R.id.fragmentFrame,listFragment);
//                    TextView textView = findViewById(R.id.testText);
//                    textView.setText("This is the list fragment");
                }else{
                    fragmentTransaction.show(listFragment);
                }
                break;
            case R.id.statTab:
                setAllFalse();
                statTab.setSelected(true);
                if(statFragment==null){
                    statFragment=new StatFragment();
                    fragmentTransaction.add(R.id.fragmentFrame,statFragment);
                }else {
                    fragmentTransaction.show(statFragment);
                }
                break;
            case R.id.createTab:
                setAllFalse();
//                polyLinear.setSelected(true);
//                if(fragmentPoly==null){
//                    fragmentPoly=new FragmentTest("Polymer");
//                    fragmentTransaction.add(R.id.fragment_frame,fragmentPoly);
//                }else {
//                    fragmentTransaction.show(fragmentPoly);
//                }
                break;
        }
        fragmentTransaction.commit();//记得必须要commit,否则没有效果

    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if(listFragment!=null){
            fragmentTransaction.hide(listFragment);
        }
        if(statFragment!=null){
            fragmentTransaction.hide(statFragment);
        }
    }

    private void setAllFalse() {
        listTab.setSelected(false);
        createTab.setSelected(false);
        statTab.setSelected(false);
        //userLinear.setSelected(false);
    }
}
