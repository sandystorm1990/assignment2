package au.edu.utas.shiduoz.assignment2.views;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import au.edu.utas.shiduoz.assignment2.R;
public class DetailActivity extends AppCompatActivity implements View.OnClickListener{

    Button backBtn, saveGoBtn;

    //fragment
    Button activityBtn, descriptionBtn, locationBtn, mediaBtn, weatherBtn;

    private ActivityFragment activityFragment;
    private DescriptionFragment descriptionFragment;
    private LocationFragment locationFragment;
    private MediaFragment mediaFragment;
    private WeatherFragment weatherFragment;
    private FragmentManager fragmentManager;

    TextView detailTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // back action
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });

        // save actioin
        saveGoBtn = findViewById(R.id.saveGoDetailBtn);
        saveGoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // fragment
        activityBtn = findViewById(R.id.activityBtn);
        descriptionBtn = findViewById(R.id.descriptionBtn);
        locationBtn = findViewById(R.id.locationBtn);
        mediaBtn = findViewById(R.id.mediaBtn);
        weatherBtn = findViewById(R.id.weatherBtn);
        activityBtn.setOnClickListener(this);
        descriptionBtn.setOnClickListener(this);
        locationBtn.setOnClickListener(this);
        mediaBtn.setOnClickListener(this);
        weatherBtn.setOnClickListener(this);

        detailTitle = findViewById(R.id.detailTitle);

        fragmentManager = getSupportFragmentManager();
        activityBtn.performClick();

    }

    @Override
    public void onClick(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();//only limit to local scope
        //FragmentTransaction can only be use once
        hideAllFragment(fragmentTransaction);
        switch (view.getId()){
            case R.id.activityBtn:
                setAllFalse();
                activityBtn.setSelected(true);
                activityBtn.setBackgroundColor(getResources().getColor(R.color.detailBg));
                activityBtn.setTextColor(Color.WHITE);
                detailTitle.setText(getResources().getString(R.string.activityDetail));
                //Log.d("Fragment tab", "asdf");
                if (activityFragment==null){
                    activityFragment=new ActivityFragment();
                    fragmentTransaction.add(R.id.fragmentDetail, activityFragment);
                }else{
                    fragmentTransaction.show(activityFragment);
                }
                break;
            case R.id.weatherBtn:
                setAllFalse();
                weatherBtn.setSelected(true);
                weatherBtn.setBackgroundColor(getResources().getColor(R.color.detailBg));
                weatherBtn.setTextColor(Color.WHITE);
                detailTitle.setText(getResources().getString(R.string.weatherDetail));
                //Log.d("Fragment tab", "qqq");
                if(weatherFragment==null){
                    weatherFragment=new WeatherFragment();
                    fragmentTransaction.add(R.id.fragmentDetail, weatherFragment);
                }else {
                    fragmentTransaction.show(weatherFragment);
                }
                break;
            case R.id.descriptionBtn:
                setAllFalse();
                descriptionBtn.setSelected(true);
                descriptionBtn.setBackgroundColor(getResources().getColor(R.color.detailBg));
                descriptionBtn.setTextColor(Color.WHITE);
                detailTitle.setText(getResources().getString(R.string.desciptionDetail));
                if(descriptionFragment==null){
                    descriptionFragment=new DescriptionFragment();
                    fragmentTransaction.add(R.id.fragmentDetail,descriptionFragment);
                }else {
                    fragmentTransaction.show(descriptionFragment);
                }
                break;
            case R.id.mediaBtn:
                setAllFalse();
                mediaBtn.setSelected(true);
                mediaBtn.setBackgroundColor(getResources().getColor(R.color.detailBg));
                mediaBtn.setTextColor(Color.WHITE);
                detailTitle.setText(getResources().getString(R.string.mediaDetail));
                if(mediaFragment==null){
                    mediaFragment=new MediaFragment();
                    fragmentTransaction.add(R.id.fragmentDetail,mediaFragment);
                }else {
                    fragmentTransaction.show(mediaFragment);
                }
                break;
            case R.id.locationBtn:
                setAllFalse();
                locationBtn.setSelected(true);
                locationBtn.setBackgroundColor(getResources().getColor(R.color.detailBg));
                locationBtn.setTextColor(Color.WHITE);
                detailTitle.setText(getResources().getString(R.string.locationDetail));
                if(locationFragment==null){
                    locationFragment=new LocationFragment();
                    fragmentTransaction.add(R.id.fragmentDetail,locationFragment);
                }else {
                    fragmentTransaction.show(locationFragment);
                }
                break;
        }
        fragmentTransaction.commit();

    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if(activityFragment!=null){
            fragmentTransaction.hide(activityFragment);
        }
        if(weatherFragment!=null){
            fragmentTransaction.hide(weatherFragment);
        }
        if(descriptionFragment!=null){
            fragmentTransaction.hide(descriptionFragment);
        }
        if(mediaFragment!=null){
            fragmentTransaction.hide(mediaFragment);
        }
        if(locationFragment!=null){
            fragmentTransaction.hide(locationFragment);
        }
    }

    private void setAllFalse() {
        activityBtn.setSelected(false);
        weatherBtn.setSelected(false);
        descriptionBtn.setSelected(false);
        mediaBtn.setSelected(false);
        locationBtn.setSelected(false);
        // setting color
        activityBtn.setBackgroundColor(Color.TRANSPARENT);
        weatherBtn.setBackgroundColor(Color.TRANSPARENT);
        descriptionBtn.setBackgroundColor(Color.TRANSPARENT);
        mediaBtn.setBackgroundColor(Color.TRANSPARENT);
        locationBtn.setBackgroundColor(Color.TRANSPARENT);

        activityBtn.setTextColor(Color.BLACK);
        weatherBtn.setTextColor(Color.BLACK);
        descriptionBtn.setTextColor(Color.BLACK);
        mediaBtn.setTextColor(Color.BLACK);
        locationBtn.setTextColor(Color.BLACK);
    }
}
