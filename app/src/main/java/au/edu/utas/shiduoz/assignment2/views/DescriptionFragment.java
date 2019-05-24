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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import au.edu.utas.shiduoz.assignment2.R;

public class DescriptionFragment extends Fragment {
    public static String mDescription = "";
    EditText editDescription;
    Button confirmBtn;
    public DescriptionFragment() {}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflateView = inflater.inflate(R.layout.fragment_description, container, false);
        Log.d("ttt", "zzz");

        editDescription = inflateView.findViewById(R.id.editDescription);
        editDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setmDescription(editDescription.getText().toString());
                editDescription.setText(getmDescription());
                Log.d("ttt", editDescription.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                setmDescription(editDescription.getText().toString());
                editDescription.setText(getmDescription());
                Log.d("ttt", editDescription.getText().toString());
            }
        });

        return inflateView;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void setmDescription(String description)
    {
        mDescription = description;
    }

    public String getmDescription()
    {
        return mDescription;
    }
}
