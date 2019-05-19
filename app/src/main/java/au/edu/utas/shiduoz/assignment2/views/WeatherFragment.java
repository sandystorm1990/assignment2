package au.edu.utas.shiduoz.assignment2.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import au.edu.utas.shiduoz.assignment2.R;

public class WeatherFragment extends Fragment {
    public WeatherFragment() {}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflateView = inflater.inflate(R.layout.fragment_weather, container, false);
        return inflateView;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
}
