package au.edu.utas.shiduoz.assignment2.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import au.edu.utas.shiduoz.assignment2.R;

public class LocationFragment extends Fragment {

    public static String mLocation;
    EditText editLocation;
    public LocationFragment() {}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflateView = inflater.inflate(R.layout.fragment_location, container, false);
        editLocation = inflateView.findViewById(R.id.locationFilter);
        editLocation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setmLocation(editLocation.getText().toString());
                editLocation.setText(getmLocation());
                Log.d("ttt-on", editLocation.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                setmLocation(editLocation.getText().toString());
                editLocation.setText(getmLocation());
                Log.d("ttt-after", editLocation.getText().toString());
            }
        });

        return inflateView;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void setmLocation(String location)
    {
        mLocation = location;
    }

    public String getmLocation()
    {
        return mLocation;
    }
}
