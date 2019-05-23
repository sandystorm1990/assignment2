package au.edu.utas.shiduoz.assignment2.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import au.edu.utas.shiduoz.assignment2.R;

public class LocationFragment extends Fragment {

    public static String mLocation;
    EditText editLocation;
    Button confirmBtn;
    public LocationFragment() {}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflateView = inflater.inflate(R.layout.fragment_location, container, false);
        editLocation = inflateView.findViewById(R.id.locationFilter);
        confirmBtn = inflateView.findViewById(R.id.locationConfirmBtn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setmLocation(editLocation.getText().toString());
                Log.d("ttt", editLocation.getText().toString());
            }
        });
        editLocation.setText(getmLocation());

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
