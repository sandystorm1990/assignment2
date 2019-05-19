package au.edu.utas.shiduoz.assignment2.views;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import au.edu.utas.shiduoz.assignment2.R;
import au.edu.utas.shiduoz.assignment2.data.Database;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

//    int mYear, mMonth, mDay;
//    final int DATE_DIALOG = 1;
//    TextView selectedDate;
//    Button btn;
//    final SpannableStringBuilder style = new SpannableStringBuilder();

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

        //
        listTab = findViewById(R.id.listTab);
        statTab = findViewById(R.id.statTab);
        createTab = findViewById(R.id.createTab);
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();//only limit to local scope
        //FragmentTransaction can only be use once
        hideAllFragment(fragmentTransaction);
        switch (view.getId()){
            case R.id.listTab:
                setAllFalse();
                listTab.setSelected(true);
                if (listFragment==null){
                    listFragment=new ListFragment();
                    fragmentTransaction.add(R.id.fragmentFrame, listFragment);
                }else{
                    fragmentTransaction.show(listFragment);
                }
                break;
            case R.id.statTab:
                setAllFalse();
                statTab.setSelected(true);
                if(statFragment==null){
                    statFragment=new StatFragment();
                    fragmentTransaction.add(R.id.fragmentFrame, statFragment);
                }else {
                    fragmentTransaction.show(statFragment);
                }
                break;
            case R.id.createTab:
                setAllFalse();
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(intent);
//                polyLinear.setSelected(true);
//                if(fragmentPoly==null){
//                    fragmentPoly=new FragmentTest("Polymer");
//                    fragmentTransaction.add(R.id.fragment_frame,fragmentPoly);
//                }else {
//                    fragmentTransaction.show(fragmentPoly);
//                }
                break;
        }
        fragmentTransaction.commit();

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
    }
}
