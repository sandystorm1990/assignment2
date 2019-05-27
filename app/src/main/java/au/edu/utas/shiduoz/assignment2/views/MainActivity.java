package au.edu.utas.shiduoz.assignment2.views;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import au.edu.utas.shiduoz.assignment2.R;
import au.edu.utas.shiduoz.assignment2.models.Entry;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

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

        // get tabs
        listTab = findViewById(R.id.listTab);
        createTab = findViewById(R.id.createTab);
        statTab = findViewById(R.id.statTab);
        listTab.setOnClickListener(this);
        createTab.setOnClickListener(this);
        statTab.setOnClickListener(this);

        fragmentManager = getSupportFragmentManager();
        listTab.performClick();
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
                //listTab.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
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
                CreateActivity.mEntry = new Entry();
                CreateActivity.entryId = 0;
                CreateActivity.selectedMood = "";
                CreateActivity.selectedLevel = 0;
                //CreateActivity.selectedDate = "";
                startActivity(intent);
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
