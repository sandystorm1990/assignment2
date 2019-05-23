package au.edu.utas.shiduoz.assignment2.views;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import au.edu.utas.shiduoz.assignment2.R;

public class WeatherFragment extends Fragment {

    TextView wCloudy, wRainy, wSnowy, wSunny, wThunder, wSunnyCloudy, wWindy;

    public static String mWeather = "";
    public WeatherFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflateView = inflater.inflate(R.layout.fragment_weather, container, false);

        wCloudy = inflateView.findViewById(R.id.wCloudy);
        wRainy = inflateView.findViewById(R.id.wRainy);
        wSnowy = inflateView.findViewById(R.id.wSnowy);
        wSunny = inflateView.findViewById(R.id.wSunny);
        wThunder = inflateView.findViewById(R.id.wThunder);
        wSunnyCloudy = inflateView.findViewById(R.id.wSunnyCoudy);
        wWindy = inflateView.findViewById(R.id.wWindy);

        selectWeather();
        performWeatherSelected();

        return inflateView;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void setmWeather(String weather)
    {
        mWeather = weather;
    }

    public String getmWeather()
    {
        return  mWeather;
    }

    private void selectWeather() {
        wCloudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                setmWeather("cloudy");
                wCloudy.setBackgroundColor(getResources().getColor(R.color.weatherBg));
            }
        });
        wRainy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                setmWeather("rainy");
                wRainy.setBackgroundColor(getResources().getColor(R.color.weatherBg));
            }
        });
        wSnowy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                setmWeather("snowy");
                wSnowy.setBackgroundColor(getResources().getColor(R.color.weatherBg));
            }
        });
        wSunny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                setmWeather("sunny");
                wSunny.setBackgroundColor(getResources().getColor(R.color.weatherBg));
            }
        });
        wSunnyCloudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                setmWeather("sunnyCloudy");
                wSunnyCloudy.setBackgroundColor(getResources().getColor(R.color.weatherBg));
            }
        });
        wThunder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                setmWeather("thunder");
                wThunder.setBackgroundColor(getResources().getColor(R.color.weatherBg));
            }
        });
        wWindy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                setmWeather("windy");
                wWindy.setBackgroundColor(getResources().getColor(R.color.weatherBg));
            }
        });
    }

    private void clearSelection() {
        wCloudy.setBackgroundColor(Color.TRANSPARENT);
        wRainy.setBackgroundColor(Color.TRANSPARENT);
        wSnowy.setBackgroundColor(Color.TRANSPARENT);
        wSunny.setBackgroundColor(Color.TRANSPARENT);
        wThunder.setBackgroundColor(Color.TRANSPARENT);
        wSunnyCloudy.setBackgroundColor(Color.TRANSPARENT);
        wWindy.setBackgroundColor(Color.TRANSPARENT);
    }

    private void performWeatherSelected()
    {
        switch (mWeather) {
            case "sunny":
                wSunny.setBackgroundColor(getResources().getColor(R.color.weatherBg));
                break;
            case "windy":
                wWindy.setBackgroundColor(getResources().getColor(R.color.weatherBg));
                break;
            case "thunder":
                wThunder.setBackgroundColor(getResources().getColor(R.color.weatherBg));
                break;
            case "rainy":
                wRainy.setBackgroundColor(getResources().getColor(R.color.weatherBg));
                break;
            case "snowy":
                wSnowy.setBackgroundColor(getResources().getColor(R.color.weatherBg));
                break;
            case "sunnyCloudy":
                wSunnyCloudy.setBackgroundColor(getResources().getColor(R.color.weatherBg));
                break;
            case "cloudy":
                wCloudy.setBackgroundColor(getResources().getColor(R.color.weatherBg));
                break;

                default: break;
        }
    }
}
